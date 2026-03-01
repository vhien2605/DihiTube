package dinh.hien.identity_service.adapter.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshRequestDTO {
    private String refreshToken;
}
