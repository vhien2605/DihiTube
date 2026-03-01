package dinh.hien.profile_service.adapter.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinh.hien.profile_service.adapter.event.KafkaUserCreatedEvent;
import dinh.hien.profile_service.adapter.event.UserProfileCreatedFailEvent;
import dinh.hien.profile_service.adapter.mapper.ProfileMapper;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserKafkaListener {
    private final CreateProfileUseCase createProfileUseCase;
    private final KafkaTemplate<String,String>kafkaTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = {"user-creation"})
    public void userCreatedListener(
            String jsonEvent
    ){
        log.info("Received UserCreatedEvent from kafka listener "+jsonEvent);
        try {
            var event = objectMapper.readValue(jsonEvent, KafkaUserCreatedEvent.class);
            createProfileUseCase.createProfile(ProfileMapper.toCreateProfileCommand(event));
        } catch (JsonProcessingException e) {
            log.error("Deserialize failed", e);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            try {
                kafkaTemplate.send("profile-creation-failed",
                        objectMapper.writeValueAsString(
                                UserProfileCreatedFailEvent.builder()
                                        .message(e.getMessage())
                                        .build()));
            } catch (Exception ex) {
                log.error("Kafka send failed", ex);
            }
        }
    }
}
