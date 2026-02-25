package dinh.hien.profile_service.application.usecase.profile;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProfileCommand {
    private String userId;
    private String displayName;
    private String phoneNumber;
}
