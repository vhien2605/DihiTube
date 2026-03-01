package dinh.hien.identity_service.infra.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@RedisHash("access_token")
public class AccessToken {
    @Id
    private String jti;
    private String userId;

    @TimeToLive
    private long ttl;
}
