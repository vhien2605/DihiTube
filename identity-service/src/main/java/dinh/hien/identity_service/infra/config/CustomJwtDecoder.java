package dinh.hien.identity_service.infra.config;

import com.nimbusds.jose.JWSAlgorithm;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.infra.service.TokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {
    @Value("${security.jwt.access.secretKey}")
    private String accessKey;

    private final TokenServiceImpl jwtService;

    private NimbusJwtDecoder nimbusJwtDecoder;

    @Override
    public Jwt decode(String token) throws JwtException {
        if (nimbusJwtDecoder == null) {
            SecretKey key = new SecretKeySpec(accessKey.getBytes(), JWSAlgorithm.HS256.toString());
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(key)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }
        jwtService.verify(token, TokenType.ACCESS);
        return nimbusJwtDecoder.decode(token);
    }
}
