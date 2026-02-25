package dinh.hien.identity_service.domain.role;



import java.util.Optional;

public interface IRoleRepository {
    Optional<Role> findByName(String name);
}
