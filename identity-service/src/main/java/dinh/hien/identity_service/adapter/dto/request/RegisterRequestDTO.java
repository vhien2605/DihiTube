package dinh.hien.identity_service.adapter.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String email;
    //profile
    private String displayName;
    private String phoneNumber;
}
