package dinh.hien.identity_service.infra.persistence.repository;

import dinh.hien.identity_service.infra.model.JpaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<JpaRole, String> {
    @Query("SELECT r FROM JpaRole r WHERE r.name = :name")
    Optional<JpaRole> findByName(@Param("name") String name);
}
