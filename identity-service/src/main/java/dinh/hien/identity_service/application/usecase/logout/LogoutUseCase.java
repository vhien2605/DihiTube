package dinh.hien.identity_service.application.usecase.logout;


import dinh.hien.identity_service.application.service.token.ITokenRepository;
import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenProperties;
import dinh.hien.identity_service.application.service.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutUseCase {
    private final ITokenRepository tokenRepository;
    private final ITokenService tokenService;

    public String logout(LogoutCommand command) {
        TokenProperties properties = tokenService.getProperties(command.getAccessToken());
        tokenRepository.saveToken(properties, TokenType.ACCESS);
        return "old token is disabled " + command.getAccessToken();
    }
}
