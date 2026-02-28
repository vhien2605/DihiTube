package dinh.hien.identity_service.application.external.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import dinh.hien.identity_service.domain.user.event.UserCreatedEvent;

public interface UserEventPublisher {
    void publishUserCreated(UserCreatedEvent event);
}
