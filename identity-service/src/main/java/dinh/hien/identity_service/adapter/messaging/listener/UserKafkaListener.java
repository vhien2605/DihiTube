package dinh.hien.identity_service.adapter.messaging.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinh.hien.identity_service.adapter.mapper.AuthMapper;
import dinh.hien.identity_service.adapter.messaging.event.UserProfileCreatedFailEvent;
import dinh.hien.identity_service.application.usecase.register.ProfileCreatedFailUseCase;
import dinh.hien.identity_service.application.usecase.register.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserKafkaListener {
    private final ObjectMapper objectMapper;
    private final ProfileCreatedFailUseCase profileCreatedFailUseCase;

    @KafkaListener(topics = {"profile-creation-failed"})
    public void userCreatedListener(
            String jsonEvent
    ){
        log.info("Received UserFailEvent from kafka listener "+jsonEvent);
        try {
            var event = objectMapper.readValue(jsonEvent, UserProfileCreatedFailEvent.class);
            profileCreatedFailUseCase.failProfile(AuthMapper.toFailCommand(event));
        } catch (JsonProcessingException e) {
            log.error("Deserialize failed", e);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
