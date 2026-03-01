package dinh.hien.identity_service.application.usecase.logout;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogoutCommand {
    private String accessToken;
    private String refreshToken;
}
