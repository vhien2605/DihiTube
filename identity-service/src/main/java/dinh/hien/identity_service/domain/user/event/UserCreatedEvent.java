package dinh.hien.identity_service.domain.user.event;


import dinh.hien.identity_service.domain.user.UserId;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent implements Serializable {
    private UserId userId;
    private String displayName;
    private String phoneNumber;
}
