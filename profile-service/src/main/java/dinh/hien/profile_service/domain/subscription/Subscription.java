package dinh.hien.profile_service.domain.subscription;

import dinh.hien.profile_service.domain.profile.ProfileId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private SubscriptionId id;
    private ProfileId profileId;
    private SubscriptionType type;
    private Date startDate;
    private Date endDate;
}
