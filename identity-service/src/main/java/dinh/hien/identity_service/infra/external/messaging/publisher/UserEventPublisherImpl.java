package dinh.hien.identity_service.infra.external.publisher;

import dinh.hien.identity_service.application.external.publisher.UserEventPublisher;
import dinh.hien.identity_service.domain.user.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventPublisherImpl implements UserEventPublisher {
    private final KafkaTemplate<String,Object>kafkaTemplate;
    @Override
    public void publishUserCreated(UserCreatedEvent event) {
        kafkaTemplate.send("user-creation",event);
    }
}
