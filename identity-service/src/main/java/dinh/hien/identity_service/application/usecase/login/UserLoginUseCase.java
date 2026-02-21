package dinh.hien.identity_service.application.usecase.login;


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
    public LoginResult userLogin(LoginCommand loginCommand){
        String username= loginCommand.getUsername();
        String rawPassword= loginCommand.getPassword();
        User user=userRepository.findByUsername(username)
                .orElseThrow(()->new DomainException(DError.USER_NOT_EXISTED));

        // check password
        user.authenticatePassword(rawPassword,passwordHasher);

        // return jwt
        return null;
    }
}
