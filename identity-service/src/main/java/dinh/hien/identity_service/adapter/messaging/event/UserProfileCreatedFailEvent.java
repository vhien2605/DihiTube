package dinh.hien.identity_service.adapter.messaging.event;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileCreatedFailEvent {
    private String userId;
    private String message;
}
