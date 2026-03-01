package dinh.hien.profile_service.domain.profile;


import dinh.hien.profile_service.domain.subscription.Subscription;
import dinh.hien.profile_service.domain.subscription.SubscriptionId;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private ProfileId id;
    private UserId userId;
    private String displayName;
    private String avatarUrl;
    private PhoneNumber phoneNumber;
    private Subscription subscription;

    public void standardSubscribe() {
        SubscriptionId subscriptionId = SubscriptionId.generate();
        this.subscription = new Subscription(
                subscriptionId,
                SubscriptionType.STANDARD
        );
    }
}
