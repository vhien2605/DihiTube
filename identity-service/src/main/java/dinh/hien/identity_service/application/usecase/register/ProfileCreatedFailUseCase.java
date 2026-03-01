package dinh.hien.identity_service.application.usecase.register;

import dinh.hien.identity_service.domain.user.IUserRepository;
import dinh.hien.identity_service.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileCreatedFailUseCase {
    private final IUserRepository userRepository;
    public void failProfile(FailCommand failCommand){
        boolean isUserExisted=userRepository.existedById(UserId.of(failCommand.getUserId()));
        if(isUserExisted){
            userRepository.deleteById(UserId.of(failCommand.getUserId()));
        }
    }
}
