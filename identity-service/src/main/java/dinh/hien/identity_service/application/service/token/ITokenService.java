package dinh.hien.identity_service.application.service.token;
import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    String generate(TokenPayload payload, TokenType type);

    TokenPayload verify(String token, TokenType type);
}
