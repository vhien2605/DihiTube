package dinh.hien.identity_service.application.service.token;


public interface ITokenService {
    String generate(TokenPayload payload, TokenType type);

    TokenPayload verify(String token, TokenType type);

    TokenProperties getProperties(String token);
}
