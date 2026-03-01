package dinh.hien.identity_service.infra.persistence.repository;

import dinh.hien.identity_service.infra.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JpaRefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
