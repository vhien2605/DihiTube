package dinh.hien.profile_service.adapter.listener;


import dinh.hien.profile_service.adapter.event.UserCreatedEvent;
import dinh.hien.profile_service.adapter.event.UserProfileCreatedFailEvent;
import dinh.hien.profile_service.adapter.mapper.ProfileMapper;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileCommand;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserKafkaListener {
    private final CreateProfileUseCase createProfileUseCase;
    private final KafkaTemplate<String,Object>kafkaTemplate;

    @KafkaListener(topics = {"user-creation"})
    public void userCreatedListener(
            UserCreatedEvent event
    ){
        try{
            CreateProfileCommand command= ProfileMapper.toCreateProfileCommand(event);
            createProfileUseCase.createProfile(command);
        } catch (Exception e) {
            var fail=UserProfileCreatedFailEvent.builder()
                    .userId(event.getUserId())
                    .message(e.getMessage())
                    .build();
            kafkaTemplate.send("profile-creation-failed",fail);
        }
    }
}
