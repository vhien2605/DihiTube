package dinh.hien.identity_service.application.usecase.register;

import dinh.hien.identity_service.application.external.publisher.UserEventPublisher;
import dinh.hien.identity_service.domain.exception.DError;
import dinh.hien.identity_service.domain.exception.DomainException;
import dinh.hien.identity_service.domain.role.IRoleRepository;
import dinh.hien.identity_service.domain.role.Role;
import dinh.hien.identity_service.domain.user.*;
import dinh.hien.identity_service.domain.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterUseCase {
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final UserEventPublisher userEventPublisher;


    public String registerUser(RegisterCommand command){
        var wrapper=userRepository.findByUsername(command.getUsername());
        if(wrapper.isPresent()){
            throw new DomainException(DError.USER_EXISTED);
        }
        Role role=roleRepository.findByName("USER")
                .orElseThrow(()->new DomainException(DError.ROLE_NOT_EXISTED));

        User user=new User(
                UserId.generate(),
                command.getUsername(),
                Password.fromRawValue(command.getPassword(),passwordHasher),
                Email.of(command.getEmail()),
                role
        );
        // save user
        userRepository.save(user);
        // event
        var event =UserCreatedEvent.builder()
                .userId(user.getId().getValue().toString())
                .phoneNumber(command.getPhoneNumber())
                .displayName(command.getDisplayName())
                .build();
        userEventPublisher.publishUserCreated(event);
        return user.getId().getValue().toString()+" registered";
    }
}
