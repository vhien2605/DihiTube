package dinh.hien.profile_service.infra.service;

import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.service.IUserProfileService;
import dinh.hien.profile_service.infra.exception.InfraError;
import dinh.hien.profile_service.infra.exception.InfraException;
import dinh.hien.profile_service.infra.model.JpaProfile;
import dinh.hien.profile_service.infra.model.JpaSubscription;
import dinh.hien.profile_service.infra.persistence.JpaProfileRepository;
import dinh.hien.profile_service.infra.persistence.JpaSubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements IUserProfileService {
    private final JpaProfileRepository jpaProfileRepository;
    private final JpaSubscriptionRepository jpaSubscriptionRepository;


    @Override
    @Transactional
    public void createProfile(UserProfile userProfile) {
        String subscriptionType=userProfile.getSubscription().getType().name();
        JpaSubscription subscription=jpaSubscriptionRepository.findByType(subscriptionType).
                orElseThrow(()-> new InfraException(InfraError.SUBSCRIPTION_NOT_FOUND));
        JpaProfile profile=JpaProfile.builder()
                .displayName(userProfile.getDisplayName())
                .subscription(subscription)
                .phoneNumber(userProfile.getPhoneNumber().getValue())
                .userId(userProfile.getUserId().getValue().toString())
                .build();
        jpaProfileRepository.save(profile);
    }
}
