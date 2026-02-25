package dinh.hien.identity_service.domain.user.event;


import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent implements Serializable {
    private String userId;
    private String displayName;
    private String phoneNumber;
}
