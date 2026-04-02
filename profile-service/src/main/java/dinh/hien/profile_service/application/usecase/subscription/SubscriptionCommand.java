package dinh.hien.profile_service.application.usecase.subscription;

import dinh.hien.profile_service.domain.profile.UserId;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCommand {
    private UserId userId;
    private String subscriptionType;
}
