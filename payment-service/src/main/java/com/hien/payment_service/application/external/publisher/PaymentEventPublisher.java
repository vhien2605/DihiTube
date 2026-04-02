package com.hien.payment_service.application.external.publisher;

import com.hien.payment_service.domain.payment.event.SubscriptionPaymentSuccessEvent;

public interface PaymentEventPublisher {
    void subscriptionPaymentSuccess(SubscriptionPaymentSuccessEvent event);
}
