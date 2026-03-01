package dinh.hien.profile_service.infra.service;

import dinh.hien.profile_service.domain.exception.DError;
import dinh.hien.profile_service.domain.exception.DomainException;
import dinh.hien.profile_service.domain.profile.IProfileService;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.infra.mapper.ProfileMapper;
import dinh.hien.profile_service.infra.model.JpaProfile;
import dinh.hien.profile_service.infra.persistence.JpaProfileRepository;
import dinh.hien.profile_service.infra.utils.SharedMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements IProfileService {
    private final JpaProfileRepository jpaProfileRepository;

    @Override
    public UserProfile readMyProfile() {
        String userId = SharedMethods.getUserIdFromSecurityContext();
        JpaProfile profile = jpaProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new DomainException(DError.PROFILE_NOT_EXISTED));
        return ProfileMapper.toDomainProfile(profile);
    }
}
