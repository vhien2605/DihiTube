package dinh.hien.identity_service.application.service.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenPayload {
    private String id;
    private String username;
    private String email;
    private String role;
}
