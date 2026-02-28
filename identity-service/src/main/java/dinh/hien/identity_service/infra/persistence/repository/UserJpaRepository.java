package dinh.hien.identity_service.infra.persistence.repository;

import dinh.hien.identity_service.infra.model.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<JpaUser, String> {
    @Query("""
            SELECT u FROM JpaUser u
            JOIN FETCH u.role r
            WHERE u.username = :username
            """)
    public Optional<JpaUser> findByUsername(@Param("username") String username);
}
