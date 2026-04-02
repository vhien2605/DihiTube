package dinh.hien.profile_service.infra.persistence;

import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.subscription.ISubscriptionRepository;
import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.domain.subscription.SubscriptionId;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository implements ISubscriptionRepository {
    private final JpaSubscriptionRepository jpaSubscriptionRepository;

    @Override
    public void save(Subscription subscription) {

    }

    @Override
    public Optional<Subscription> findByType(SubscriptionType type) {
        var optional = jpaSubscriptionRepository.findByType(type.name());
        if (optional.isPresent()) {
            var entity = optional.get();
            Subscription subscription = new Subscription(
                    SubscriptionId.of(entity.getId()),
                    SubscriptionType.valueOf(entity.getType())
            );
            return Optional.of(subscription);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Subscription> findSubscriptionByUserId(UserId userId) {
        var optional = jpaSubscriptionRepository.findJpaSubscriptionByUserId(
                userId.getValue().toString()
        );
        if (optional.isPresent()) {
            var entity = optional.get();
            Subscription subscription = new Subscription(
                    SubscriptionId.of(entity.getId()),
                    SubscriptionType.valueOf(entity.getType())
            );
            return Optional.of(subscription);
        } else {
            return Optional.empty();
        }
    }
}
