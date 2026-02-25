package dinh.hien.profile_service.application.usecase.profile;


import dinh.hien.profile_service.domain.profile.PhoneNumber;
import dinh.hien.profile_service.domain.profile.ProfileId;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.service.IUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateProfileUseCase {
    private final IUserProfileService userProfileService;
    public void createProfile(CreateProfileCommand command){
        ProfileId profileId=ProfileId.generate();
        UserProfile profile=new UserProfile(
                profileId,
                UserId.of(command.getUserId()),
                command.getDisplayName(),
                "",
                PhoneNumber.of(command.getPhoneNumber()),
                null
        );
        profile.standardSubscribe();
        userProfileService.createProfile(profile);
    }
}
