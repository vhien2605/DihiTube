package dinh.hien.profile_service.infra.persistence;

import dinh.hien.profile_service.domain.profile.IProfileRepository;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.infra.mapper.ProfileMapper;
import dinh.hien.profile_service.infra.model.JpaProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfileRepository implements IProfileRepository {
    private final JpaProfileRepository jpaProfileRepository;

    @Override
    public void save(UserProfile profile) {
        JpaProfile jpaProfile = ProfileMapper.toJpaProfile(profile);
        jpaProfileRepository.save(jpaProfile);
    }

    @Override
    public Optional<UserProfile> findByUserId(UserId id) {
        var jpaProfile = jpaProfileRepository.findByUserId(id.getValue().toString());
        return jpaProfile.map(ProfileMapper::toDomainProfile);
    }
}
