package dinh.hien.profile_service.application.usecase.subscription;


import dinh.hien.profile_service.domain.exception.DError;
import dinh.hien.profile_service.domain.exception.DomainException;
import dinh.hien.profile_service.domain.profile.UserId;
import dinh.hien.profile_service.domain.subscription.ISubscriptionRepository;
import dinh.hien.profile_service.domain.subscription.SubscriptionType;
import dinh.hien.profile_service.infra.external.PaymentClient;
import dinh.hien.profile_service.adapter.dto.request.PaymentLinkRequest;
import dinh.hien.profile_service.infra.utils.SharedMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionCheckoutUseCase {
    private final ISubscriptionRepository subscriptionRepository;
    private final PaymentClient paymentClient;


    public String subscriptionCheckout(SubscriptionCommand command) {
        String subscriptionType = command.getSubscriptionType();
        //validate domain
        SubscriptionType type = SubscriptionType.valueOf(subscriptionType);
        var wrapper = subscriptionRepository.findByType(type);
        if (wrapper.isEmpty()) {
            throw new DomainException(DError.SUBSCRIPTION_NOT_EXISTED);
        }
        // check sub of user currently
        String userId = SharedMethods.getUserIdFromSecurityContext();
        var subscriptionUser = subscriptionRepository.findSubscriptionByUserId(
                UserId.of(userId)
        );
        if (subscriptionUser.isPresent() && subscriptionUser.get().getType().equals(type)) {
            return "You are already subscribed to the " + type.name();
        }

        // call payment service to get vnpay link
        String paymentLink = callPaymentServiceForLink(type);
        log.info("Payment link obtained for subscription type: {}", type.name());

        return paymentLink;
    }


    private String callPaymentServiceForLink(SubscriptionType subscriptionType) {
        PaymentLinkRequest request = PaymentLinkRequest.builder()
                .amount(BigDecimal.valueOf(subscriptionType.getPrice()))
                .currency("VND")
                .description("Checkout out for subscription " + subscriptionType.name())
                .subscriptionType(subscriptionType.name())
                .build();
        var response = paymentClient.getPaymentLink(request);
        if (response == null || response.getBody() == null || response.getBody().getData().isEmpty()) {
            throw new DomainException(DError.PAYMENT_LINK_FAILED);
        }
        return response.getBody().getData();
    }
}
