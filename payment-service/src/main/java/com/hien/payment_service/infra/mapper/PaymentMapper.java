package com.hien.payment_service.infra.mapper;

import com.hien.payment_service.domain.payment.*;
import com.hien.payment_service.infra.model.JpaPayment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentMapper {
    public static JpaPayment toJpaEntity(Payment payment) {
        if (payment == null) {
            return null;
        }

        return JpaPayment.builder()
                .id(payment.getId().getValue().toString())
                .amount(payment.getAmount().getAmount().longValue())
                .currency(payment.getAmount().getCurrency().getCode())
                .description(payment.getDescription())
                .status(payment.getStatus().name())
                .userId(payment.getUserId().getValue().toString())
                .transactionRef(payment.getTransactionRef())
                .subscriptionType(payment.getSubscriptionType())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }

    public static Payment toDomainEntity(JpaPayment jpaPayment) {
        if (jpaPayment == null) {
            return null;
        }
        PaymentId paymentId = PaymentId.of(UUID.fromString(jpaPayment.getId()));
        Money money = Money.of(
                BigDecimal.valueOf(jpaPayment.getAmount()),
                Currency.valueOf(jpaPayment.getCurrency())
        );
        UserId userId = UserId.of(UUID.fromString(jpaPayment.getUserId()));
        PaymentStatus status = PaymentStatus.valueOf(jpaPayment.getStatus());

        Payment payment = Payment.of(paymentId, money, jpaPayment.getDescription(), status, userId, jpaPayment.getSubscriptionType());
        payment.setTransactionRef(jpaPayment.getTransactionRef());

        return payment;
    }
}
