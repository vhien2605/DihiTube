package dinh.hien.profile_service.infra.mapper;

import dinh.hien.profile_service.domain.profile.PhoneNumber;
import dinh.hien.profile_service.domain.profile.ProfileId;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.domain.subscription.SubscriptionId;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import dinh.hien.profile_service.infra.model.JpaProfile;
import dinh.hien.profile_service.infra.model.JpaSubscription;

public class ProfileMapper {
    public static UserProfile toDomainProfile(JpaProfile jpaProfile) {
        return new UserProfile(
                ProfileId.of(jpaProfile.getId()),
                UserId.of(jpaProfile.getUserId()),
                jpaProfile.getDisplayName(),
                jpaProfile.getAvatarUrl(),
                PhoneNumber.of(jpaProfile.getPhoneNumber()),
                new Subscription(
                        SubscriptionId.of(jpaProfile.getSubscription().getId()),
                        SubscriptionType.valueOf(jpaProfile.getSubscription().getType().toUpperCase())
                )
        );
    }

    public static JpaProfile toJpaProfile(UserProfile userProfile) {
        return JpaProfile.builder()
                .id(userProfile.getId().getValue().toString())
                .userId(userProfile.getUserId().getValue().toString())
                .displayName(userProfile.getDisplayName())
                .avatarUrl(userProfile.getAvatarUrl())
                .phoneNumber(userProfile.getPhoneNumber().getValue())
                .subscription(JpaSubscription.builder()
                        .id(userProfile.getSubscription().getId().getValue().toString())
                        .type(userProfile.getSubscription().getType().name().toLowerCase())
                        .build())
                .build();
    }
}
