package dinh.hien.identity_service.application.service.token;

public interface ITokenRepository {
    void saveToken(TokenProperties properties, TokenType type);

    void deleteToken(String jti, TokenType type);
}
