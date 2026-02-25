package dinh.hien.identity_service.domain.user;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByUsername(String username);

    void save(User user);
}
