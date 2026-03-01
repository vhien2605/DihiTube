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
        TokenProperties accessProperties = tokenService.getProperties(command.getAccessToken());
        TokenProperties refreshProperties = tokenService.getProperties(command.getRefreshToken());
        // save access
        tokenRepository.saveToken(accessProperties, TokenType.ACCESS);
        // delete refresh
        tokenRepository.deleteToken(refreshProperties.getJti(), TokenType.REFRESH);
        return "old token is disabled " + command.getAccessToken();
    }
}
