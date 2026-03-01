package dinh.hien.identity_service.infra.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenPayload;
import dinh.hien.identity_service.application.service.token.TokenProperties;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.infra.exception.AuthException;
import dinh.hien.identity_service.infra.exception.InfraError;
import dinh.hien.identity_service.infra.persistence.repository.JpaRefreshTokenRepository;
import dinh.hien.identity_service.infra.persistence.repository.RedisAccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements ITokenService {
    @Value("${security.jwt.access.timeout}")
    private long accessTTL;
    @Value("${security.jwt.refresh.timeout}")
    private long refreshTTL;
    @Value("${security.jwt.reset.timeout}")
    private long resetTTL;
    @Value("${security.jwt.access.secretKey}")
    private String accessKey;
    @Value("${security.jwt.refresh.secretKey}")
    private String refreshKey;
    @Value("${security.jwt.reset.secretKey}")
    private String resetKey;

    private final RedisAccessRepository redisAccessRepository;
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;

    @Override
    public String generate(TokenPayload payload, TokenType type) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        long ttl = 0;
        String secretKey;
        if (type.equals(TokenType.ACCESS)) {
            ttl = accessTTL * 60 * 1000L;
            secretKey = accessKey;
        } else if (type.equals(TokenType.REFRESH)) {
            ttl = refreshTTL * 30 * 24 * 60 * 60 * 1000L;
            secretKey = refreshKey;
        } else {
            ttl = resetTTL * 60 * 1000L;
            secretKey = resetKey;
        }
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(payload.getUserId())
                .issuer("identity-service")
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + ttl))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", "ROLE_" + payload.getRole())
                .claim("username", payload.getUsername())
                .build();
        Payload jwtPayload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, jwtPayload);
        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            log.error(InfraError.JWT_SIGN_ERROR.name(), e);
            throw new AuthException(InfraError.JWT_SIGN_ERROR);
        }
        return jwsObject.serialize();
    }

    @Override
    public TokenPayload verify(String token, TokenType type) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            boolean valid = jwsObject.verify(
                    new MACVerifier(getSecretKey(type))
            );
            if (!valid)
                throw new AuthException(InfraError.JWT_INVALID_SIGNATURE);

            JWTClaimsSet claims = JWTClaimsSet.parse(
                    jwsObject.getPayload().toJSONObject()
            );

            if (claims.getExpirationTime().before(new Date()))
                throw new AuthException(InfraError.JWT_EXPIRED);


            if (isDisable(claims.getJWTID(), type)) {
                throw new AuthException(InfraError.TOKEN_DISABLE);
            }

            return new TokenPayload(
                    claims.getSubject(),
                    claims.getStringClaim("username"),
                    claims.getStringClaim("scope").replace("ROLE_", "")
            );
        } catch (Exception e) {
            throw new AuthException(InfraError.JWT_INVALID);
        }
    }

    @Override
    public TokenProperties getProperties(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWTClaimsSet claims = JWTClaimsSet.parse(
                    jwsObject.getPayload().toJSONObject()
            );
            return TokenProperties.builder()
                    .jti(claims.getJWTID())
                    .userId(claims.getSubject())
                    .issuer(claims.getIssuer())
                    .subject(claims.getSubject())
                    .ttl(calculateTtl(claims))
                    .build();
        } catch (Exception e) {
            throw new AuthException(InfraError.JWT_INVALID);
        }
    }


    private boolean isDisable(String jti, TokenType type) {
        if (type.equals(TokenType.ACCESS)) {
            var wrapper = redisAccessRepository.findById(jti);
            if (wrapper.isPresent()) {
                return true;
            }
        } else if (type.equals(TokenType.REFRESH)) {
            var wrapper = jpaRefreshTokenRepository.findById(jti);
            if (wrapper.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    private long calculateTtl(JWTClaimsSet claims) {
        Date expiration = claims.getExpirationTime();
        if (expiration == null) return 0;
        return expiration.getTime() - System.currentTimeMillis();
    }


    private String getSecretKey(TokenType type) {
        return switch (type) {
            case ACCESS -> accessKey;
            case REFRESH -> refreshKey;
            case RESET -> resetKey;
        };
    }
}
