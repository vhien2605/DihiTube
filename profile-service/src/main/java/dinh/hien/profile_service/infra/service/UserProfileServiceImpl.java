package dinh.hien.profile_service.infra.service;

import dinh.hien.profile_service.domain.profile.PhoneNumber;
import dinh.hien.profile_service.domain.profile.ProfileId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.service.IUserProfileService;
import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.domain.subscription.SubscriptionId;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import dinh.hien.profile_service.infra.exception.InfraError;
import dinh.hien.profile_service.infra.exception.InfraException;
import dinh.hien.profile_service.infra.mapper.ProfileMapper;
import dinh.hien.profile_service.infra.model.JpaProfile;
import dinh.hien.profile_service.infra.model.JpaSubscription;
import dinh.hien.profile_service.infra.persistence.JpaProfileRepository;
import dinh.hien.profile_service.infra.persistence.JpaSubscriptionRepository;
import dinh.hien.profile_service.infra.utils.SharedMethods;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements IUserProfileService {
    private final JpaProfileRepository jpaProfileRepository;
    private final JpaSubscriptionRepository jpaSubscriptionRepository;


    @Override
    @Transactional
    public void createProfile(UserProfile userProfile) {
        String subscriptionType = userProfile.getSubscription().getType().name();
        JpaSubscription subscription = jpaSubscriptionRepository.findByType(subscriptionType).
                orElseThrow(() -> new InfraException(InfraError.SUBSCRIPTION_NOT_FOUND));
        JpaProfile profile = JpaProfile.builder()
                .id(userProfile.getId().getValue().toString())
                .displayName(userProfile.getDisplayName())
                .subscription(subscription)
                .phoneNumber(userProfile.getPhoneNumber().getValue())
                .userId(userProfile.getUserId().getValue().toString())
                .build();
        jpaProfileRepository.save(profile);
    }

    @Override
    public Optional<UserProfile> getCurrentUserProfile() {
        String userId = SharedMethods.getUserIdFromSecurityContext();
        var wrapper = jpaProfileRepository.findByUserId(userId);
        if (wrapper.isPresent()) {
            JpaProfile profile = wrapper.get();
            return Optional.of(ProfileMapper.toDomainProfile(profile));
        } else {
            return Optional.empty();
        }
    }
}
