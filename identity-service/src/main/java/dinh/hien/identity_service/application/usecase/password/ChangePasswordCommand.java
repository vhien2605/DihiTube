package dinh.hien.identity_service.application.usecase.password;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordCommand {
    private String token;
    private String oldPassword;
    private String newPassword;
}
