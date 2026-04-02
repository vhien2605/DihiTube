package com.hien.payment_service.application.usecase.success;

import com.hien.payment_service.application.external.publisher.PaymentEventPublisher;
import com.hien.payment_service.domain.payment.IPaymentService;
import com.hien.payment_service.domain.payment.StatusDomainResult;
import com.hien.payment_service.domain.payment.event.SubscriptionPaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SuccessUseCase {
    private final IPaymentService paymentService;
    private final PaymentEventPublisher paymentEventPublisher;

    public void paymentSuccess(Map<String, String> params) {
        StatusDomainResult result = paymentService.handleStatus(params);
        if (result.getIsSuccess()) {
            //publisher event
            SubscriptionPaymentSuccessEvent event = new SubscriptionPaymentSuccessEvent(
                    result.getUserId().getValue().toString(),
                    result.getSubscriptionType()
            );
            paymentEventPublisher.subscriptionPaymentSuccess(event);
        }
    }
}
