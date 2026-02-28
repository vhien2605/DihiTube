package dinh.hien.profile_service.adapter.event;


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
