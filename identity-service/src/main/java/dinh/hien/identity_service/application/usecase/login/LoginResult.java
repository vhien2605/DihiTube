package dinh.hien.identity_service.application.usecase.login;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LoginResult {
    private String accessToken;
    private String refreshToken;
}
