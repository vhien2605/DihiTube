package dinh.hien.identity_service.application.usecase.register;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand {
    private String username;
    private String password;
    private String email;
    //profile
    private String displayName;
    private String phoneNumber;
}
