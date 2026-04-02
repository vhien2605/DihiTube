package dinh.hien.profile_service.application.usecase.subscription;


import dinh.hien.profile_service.domain.exception.DError;
import dinh.hien.profile_service.domain.exception.DomainException;
import dinh.hien.profile_service.domain.profile.IProfileRepository;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import dinh.hien.profile_service.infra.persistence.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscriptionPaymentSuccessUseCase {
    private final IProfileRepository profileRepository;
    private final SubscriptionRepository subscriptionRepository;

    public void success(SubscriptionCommand command) {
        String type = command.getSubscriptionType();
        UserId userId = command.getUserId();
        UserProfile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new DomainException(DError.PROFILE_NOT_EXISTED));
        Subscription subscription = subscriptionRepository.findByType(
                        SubscriptionType.valueOf(type))
                .orElseThrow(() -> new DomainException(DError.SUBSCRIPTION_NOT_EXISTED));
        profile.updateSubscription(subscription);
        profileRepository.save(profile);
    }
}
