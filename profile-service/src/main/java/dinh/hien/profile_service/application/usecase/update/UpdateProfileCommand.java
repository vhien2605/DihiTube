package dinh.hien.profile_service.application.usecase.update;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileCommand {
    private String displayName;
    private String avatarUrl;
    private String phoneNumber;
}
