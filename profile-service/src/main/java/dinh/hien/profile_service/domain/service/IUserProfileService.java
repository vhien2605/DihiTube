package dinh.hien.profile_service.domain.service;

import dinh.hien.profile_service.domain.profile.UserProfile;

import java.util.Optional;

public interface IUserProfileService {
    void createProfile(UserProfile userProfile);

    Optional<UserProfile> getCurrentUserProfile();
}
