package dinh.hien.identity_service.application.service.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenProperties {
    private String jti;
    private String userId;
    private String issuer;
    private String subject;
    private long ttl;
}
