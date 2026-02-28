package dinh.hien.identity_service.infra.persistence.impl;

import dinh.hien.identity_service.application.service.token.ITokenRepository;
import dinh.hien.identity_service.application.service.token.TokenProperties;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.infra.model.AccessToken;
import dinh.hien.identity_service.infra.model.RefreshToken;
import dinh.hien.identity_service.infra.persistence.repository.JpaRefreshTokenRepository;
import dinh.hien.identity_service.infra.persistence.repository.RedisAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class TokenRepositoryImpl implements ITokenRepository {
    private final JpaRefreshTokenRepository jpaRefreshTokenRepository;
    private final RedisAccessRepository redisAccessRepository;

    @Override
    @Transactional
    public void saveToken(TokenProperties properties, TokenType type) {
        if (type.equals(TokenType.ACCESS)) {
            AccessToken accessToken = AccessToken.builder()
                    .jti(properties.getJti())
                    .ttl(properties.getTtl())
                    .userId(properties.getUserId())
                    .build();
            redisAccessRepository.save(accessToken);
        } else {
            RefreshToken refreshToken = RefreshToken.builder()
                    .jti(properties.getJti())
                    .userId(properties.getUserId())
                    .build();
            jpaRefreshTokenRepository.save(refreshToken);
        }
    }
}
