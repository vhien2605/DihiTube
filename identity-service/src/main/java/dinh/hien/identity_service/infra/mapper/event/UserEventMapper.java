package dinh.hien.identity_service.infra.mapper.event;

import dinh.hien.identity_service.domain.user.event.UserCreatedEvent;
import dinh.hien.identity_service.infra.external.messaging.event.KafkaUserCreatedEvent;

public class UserEventMapper {
    private UserEventMapper() {
        // prevent instantiation
    }
    public static KafkaUserCreatedEvent toKafkaUserCreatedEvent(UserCreatedEvent event) {
        if (event == null) {
            return null;
        }
        return KafkaUserCreatedEvent.builder()
                .userId(event.getUserId().getValue().toString())
                .displayName(event.getDisplayName())
                .phoneNumber(event.getPhoneNumber())
                .build();
    }
}
