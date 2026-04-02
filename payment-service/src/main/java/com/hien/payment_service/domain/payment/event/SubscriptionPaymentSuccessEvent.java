package com.hien.payment_service.domain.payment.event;

import com.hien.payment_service.domain.payment.UserId;
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
