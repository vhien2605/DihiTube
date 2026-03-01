package dinh.hien.identity_service.infra.external.messaging.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinh.hien.identity_service.application.external.publisher.UserEventPublisher;
import dinh.hien.identity_service.domain.user.event.UserCreatedEvent;
import dinh.hien.identity_service.infra.mapper.event.UserEventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserEventPublisherImpl implements UserEventPublisher {
    private final KafkaTemplate<String,String>kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publishUserCreated(UserCreatedEvent event){
        try {
            String jsonEvent=objectMapper.writeValueAsString(UserEventMapper.toKafkaUserCreatedEvent(event));
            kafkaTemplate.send("user-creation", jsonEvent);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
