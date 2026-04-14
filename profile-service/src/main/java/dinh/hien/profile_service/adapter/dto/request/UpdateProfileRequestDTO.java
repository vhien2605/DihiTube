package dinh.hien.profile_service.adapter.dto.request;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequestDTO {
    private String displayName;
    private String avatarUrl;
    private String phoneNumber;
}
