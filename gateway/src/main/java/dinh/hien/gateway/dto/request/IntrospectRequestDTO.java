package dinh.hien.gateway.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectRequestDTO {
    private String accessToken;
}
