package dinh.hien.profile_service.application.usecase.readprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadProfileResult {
    private String id;
    private String userId;
    private String displayName;
    private String avatarUrl;
    private String phoneNumber;
    private String subscription;
}
