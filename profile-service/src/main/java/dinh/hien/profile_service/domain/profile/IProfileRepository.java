package dinh.hien.profile_service.domain.profile;

import java.util.Optional;

public interface IProfileRepository {
    void save(UserProfile profile);

    Optional<UserProfile> findByUserId(UserId id);
}
