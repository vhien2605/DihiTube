package dinh.hien.identity_service.infra.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenPayload;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.infra.exception.AuthException;
import dinh.hien.identity_service.infra.exception.InfraError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


@Service
@Slf4j
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
                .subject(payload.getId())
                .issuer("identity-service")
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + ttl))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", "ROLE_"+ payload.getRole())
                .claim("username",payload.getUsername())
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

            return new TokenPayload(
                    claims.getSubject(),
                    claims.getStringClaim("username"),
                    claims.getStringClaim("scope").replace("ROLE_", "")
            );
        } catch (ParseException | JOSEException e) {
            throw new AuthException(InfraError.JWT_INVALID);
        }
    }

    private String getSecretKey(TokenType type) {
        return switch (type) {
            case ACCESS -> accessKey;
            case REFRESH -> refreshKey;
            case RESET -> resetKey;
        };
    }
}
