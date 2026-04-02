package com.hien.payment_service.infra.external.messaging.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hien.payment_service.application.external.publisher.PaymentEventPublisher;
import com.hien.payment_service.domain.payment.event.SubscriptionPaymentSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaPaymentPublisherImpl implements PaymentEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void subscriptionPaymentSuccess(SubscriptionPaymentSuccessEvent event) {
        try {
            String jsonEvent = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("subscription-payment-success", jsonEvent);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}
