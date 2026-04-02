package dinh.hien.profile_service.application.usecase.subscription;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionCommand {
    private String subscriptionType;
}
