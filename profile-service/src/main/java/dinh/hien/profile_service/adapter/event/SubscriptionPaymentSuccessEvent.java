package dinh.hien.profile_service.adapter.event;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPaymentSuccessEvent {
    private String userId;
    private String subscriptionType;
}
