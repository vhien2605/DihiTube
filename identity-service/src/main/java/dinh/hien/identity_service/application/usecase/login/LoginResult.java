package dinh.hien.identity_service.application.usecase.login;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class LoginResult {
    private String accessToken;
    private String refreshToken;
}
