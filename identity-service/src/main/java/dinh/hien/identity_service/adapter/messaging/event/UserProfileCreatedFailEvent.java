package dinh.hien.profile_service.adapter.event;

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
