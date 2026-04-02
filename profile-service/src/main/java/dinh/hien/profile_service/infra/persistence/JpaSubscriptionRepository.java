package dinh.hien.profile_service.infra.persistence;

import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.infra.model.JpaSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaSubscriptionRepository extends JpaRepository<JpaSubscription, String> {
    @Query("""
            SELECT s FROM JpaSubscription s
            WHERE s.type=:type
            """)
    Optional<JpaSubscription> findByType(@Param("type") String type);

    @Query("""
            SELECT s FROM JpaProfile p
            JOIN p.subscription s
            WHERE p.userId=:userId
            """)
    Optional<JpaSubscription> findJpaSubscriptionByUserId(@Param("userId") String userId);
}
