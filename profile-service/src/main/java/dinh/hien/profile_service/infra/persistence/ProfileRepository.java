package dinh.hien.profile_service.infra.persistence;

import dinh.hien.profile_service.domain.profile.IProfileRepository;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProfileRepository implements IProfileRepository {
    @Override
    public void save(UserProfile profile) {
        
    }

    @Override
    public Optional<UserProfile> findByUserId(UserId id) {
        return Optional.empty();
    }
}
