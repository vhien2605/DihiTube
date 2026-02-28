package dinh.hien.identity_service.infra.persistence.repository;

import dinh.hien.identity_service.infra.model.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisAccessRepository extends CrudRepository<AccessToken, String> {
}
