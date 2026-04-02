package dinh.hien.profile_service.adapter.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dinh.hien.profile_service.adapter.event.SubscriptionPaymentSuccessEvent;
import dinh.hien.profile_service.adapter.mapper.ProfileMapper;
import dinh.hien.profile_service.application.usecase.subscription.SubscriptionPaymentSuccessUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentKafkaListener {
    private final SubscriptionPaymentSuccessUseCase subscriptionPaymentSuccessUseCase;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = {"subscription-payment-success"})
    public void userCreatedListener(
            String jsonEvent
    ) {
        log.info("Received payment success event from kafka listener " + jsonEvent);
        try {
            var event = objectMapper.readValue(jsonEvent, SubscriptionPaymentSuccessEvent.class);
            subscriptionPaymentSuccessUseCase.success(ProfileMapper.toSubscriptionCommand(event));
        } catch (JsonProcessingException e) {
            log.error("Deserialize failed", e);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
