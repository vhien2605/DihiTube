package dinh.hien.identity_service.adapter.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDTO {
    private String oldPassword;
    private String newPassword;
}
