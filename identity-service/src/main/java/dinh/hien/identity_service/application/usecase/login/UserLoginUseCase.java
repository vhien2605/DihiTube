package dinh.hien.identity_service.application.usecase.login;


import dinh.hien.identity_service.application.service.token.ITokenRepository;
import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenPayload;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.domain.exception.DError;
import dinh.hien.identity_service.domain.exception.DomainException;
import dinh.hien.identity_service.domain.user.IUserRepository;
import dinh.hien.identity_service.domain.user.PasswordHasher;
import dinh.hien.identity_service.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginUseCase {
    private final IUserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final ITokenService tokenService;
    private final ITokenRepository tokenRepository;

    public LoginResult loginUser(LoginCommand loginCommand) {
        String username = loginCommand.getUsername();
        String rawPassword = loginCommand.getPassword();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DomainException(DError.USER_NOT_EXISTED));
        // check password
        user.authenticatePassword(rawPassword, passwordHasher);
        // return jwt
        TokenPayload payload = TokenPayload.builder()
                .userId(user.getId().getValue().toString())
                .username(user.getUsername())
                .role(user.getRole().getName())
                .build();
        String accessToken = tokenService.generate(payload, TokenType.ACCESS);
        String refreshToken = tokenService.generate(payload, TokenType.REFRESH);
        tokenRepository.saveToken(tokenService.getProperties(refreshToken), TokenType.REFRESH);

        return LoginResult.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
