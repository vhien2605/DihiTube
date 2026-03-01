package dinh.hien.identity_service.domain.user;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByUsername(String username);

    Optional<User> findById(UserId id);

    boolean existedById(UserId id);

    void save(User user);

    void deleteById(UserId id);
}
