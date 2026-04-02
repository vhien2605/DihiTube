package dinh.hien.profile_service.domain.subscription;


import dinh.hien.profile_service.domain.profile.UserId;

import java.util.Optional;

public interface ISubscriptionRepository {
    void save(Subscription subscription);

    Optional<Subscription> findByType(SubscriptionType type);

    Optional<Subscription> findSubscriptionByUserId(UserId userId);
}
