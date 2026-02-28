package dinh.hien.identity_service.adapter.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectRequestDTO {
    private String accessToken;
}
