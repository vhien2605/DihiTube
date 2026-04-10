package dinh.hien.profile_service.application.usecase.update;


import dinh.hien.profile_service.domain.exception.DError;
import dinh.hien.profile_service.domain.exception.DomainException;
import dinh.hien.profile_service.domain.profile.IProfileRepository;
import dinh.hien.profile_service.domain.profile.PhoneNumber;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.service.IUserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProfileUseCase {
    private final IUserProfileService userProfileService;
    private final IProfileRepository profileRepository;

    public String update(UpdateProfileCommand updateProfileCommand) {
        var wrapper = userProfileService.getCurrentUserProfile();
        if (wrapper.isEmpty()) {
            throw new DomainException(DError.PROFILE_NOT_EXISTED);
        }
        UserProfile userProfile = wrapper.get();
        // update profile
        userProfile.profileUpdate(updateProfileCommand.getDisplayName()
                , updateProfileCommand.getAvatarUrl(),
                PhoneNumber.of(updateProfileCommand.getPhoneNumber())
        );
        profileRepository.save(userProfile);
        return "ok";
    }
}
