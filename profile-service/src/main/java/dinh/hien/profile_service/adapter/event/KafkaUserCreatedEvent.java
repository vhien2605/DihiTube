package dinh.hien.profile_service.adapter.event;


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
