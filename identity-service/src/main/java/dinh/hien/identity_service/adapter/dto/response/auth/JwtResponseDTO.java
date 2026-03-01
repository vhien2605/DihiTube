package dinh.hien.identity_service.adapter.dto.response.auth;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponseDTO {
    private String accessToken;
    private String refreshToken;
}
