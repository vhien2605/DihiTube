package dinh.hien.profile_service.infra.persistence;

import dinh.hien.profile_service.infra.model.JpaProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaProfileRepository extends JpaRepository<JpaProfile, String> {
    @Query("""
            SELECT p FROM JpaProfile p
            JOIN FETCH p.subscription a
            WHERE p.userId=:userId
            """)
    Optional<JpaProfile> findByUserId(String userId);
}
