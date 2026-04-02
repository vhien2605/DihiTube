package dinh.hien.identity_service.application.usecase.password;

import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenPayload;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.domain.exception.DError;
import dinh.hien.identity_service.domain.exception.DomainException;
import dinh.hien.identity_service.domain.user.IUserRepository;
import dinh.hien.identity_service.domain.user.PasswordHasher;
import dinh.hien.identity_service.domain.user.User;
import dinh.hien.identity_service.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordUseCase {
    private final PasswordHasher passwordHasher;
    private final ITokenService tokenService;
    private final IUserRepository userRepository;

    public String password(ChangePasswordCommand command) {
        String token = command.getToken();
        String oldPassword = command.getOldPassword();
        String newPassword = command.getNewPassword();
        TokenPayload payload = tokenService.verify(token, TokenType.ACCESS);
        User user = userRepository.findById(UserId.of(payload.getUserId()))
                .orElseThrow(() -> new DomainException(DError.USER_NOT_EXISTED));
        user.changePassword(oldPassword, newPassword, passwordHasher);
        userRepository.save(user);
        return "Changed Password";
    }
}
