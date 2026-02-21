package dinh.hien.identity_service.application.usecase.login;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginCommand {
    private String username;
    private String password;
}
