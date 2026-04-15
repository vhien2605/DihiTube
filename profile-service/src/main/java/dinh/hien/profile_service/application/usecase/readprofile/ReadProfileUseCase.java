package dinh.hien.profile_service.application.usecase.readprofile;

import dinh.hien.profile_service.domain.profile.IProfileService;
import dinh.hien.profile_service.domain.profile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadProfileUseCase {
    private final IProfileService profileService;

    public ReadProfileResult readProfile() {
        UserProfile userProfile = profileService.readMyProfile();
        return ReadProfileResult.builder()
                .id(userProfile.getId().getValue().toString())
                .userId(userProfile.getUserId().getValue().toString())
                .avatarUrl(userProfile.getAvatarUrl())
                .displayName(userProfile.getDisplayName())
                .phoneNumber(userProfile.getPhoneNumber().getValue())
                .subscription(userProfile.getSubscription().getType().name())
                .build();
    }

    public boolean isMembership() {
        return profileService.isMembership();
    }
}
