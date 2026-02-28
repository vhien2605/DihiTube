package dinh.hien.identity_service.infra.external.messaging.event;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaUserCreatedEvent {
    private String userId;
    private String displayName;
    private String phoneNumber;
}
