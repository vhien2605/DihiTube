package dinh.hien.identity_service.application.service.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenPayload {
    private String userId;
    private String username;
    private String role;
}
