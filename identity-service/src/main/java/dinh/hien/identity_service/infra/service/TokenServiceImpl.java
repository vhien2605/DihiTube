package dinh.hien.identity_service.infra.service;

import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenPayload;
import dinh.hien.identity_service.application.service.token.TokenType;
import org.springframework.stereotype.Service;


@Service
public class TokenServiceImpl implements ITokenService {
    @Override
    public String generate(TokenPayload payload, TokenType type) {
        return "";
    }

    @Override
    public TokenPayload verify(String token, TokenType type) {
        return null;
    }
}
