package dinh.hien.profile_service.infra.mapper;

import dinh.hien.profile_service.domain.profile.PhoneNumber;
import dinh.hien.profile_service.domain.profile.ProfileId;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.profile.UserProfile;
import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.domain.subscription.SubscriptionId;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import dinh.hien.profile_service.infra.model.JpaProfile;

public class ProfileMapper {
//    private ProfileId id;
//    private UserId userId;
//    private String displayName;
//    private String avatarUrl;
//    private PhoneNumber phoneNumber;
//    private Subscription subscription;

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
}
