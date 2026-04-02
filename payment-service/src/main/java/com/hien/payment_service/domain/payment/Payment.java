package com.hien.payment_service.domain.payment;

import com.hien.payment_service.domain.exception.DError;
import com.hien.payment_service.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Payment {
    private final PaymentId id;
    private final Money amount;
    private final String description;
    private PaymentStatus status;
    private final UserId userId;
    @Setter
    private String transactionRef;
    private String subscriptionType;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Payment(PaymentId id, Money amount, String description, PaymentStatus status, UserId userId, String subscriptionType) {
        validate(amount, description);
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.userId = userId;
        this.subscriptionType = subscriptionType;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static Payment create(Money amount, String description, UserId userId, String subscriptionType) {
        return new Payment(PaymentId.generate(), amount, description, PaymentStatus.PROCESSING, userId, subscriptionType);
    }

    public static Payment of(PaymentId id, Money amount, String description, PaymentStatus status, UserId userId, String subscriptionType) {
        return new Payment(id, amount, description, status, userId, subscriptionType);
    }

    private static void validate(Money amount, String description) {
        if (amount == null) {
            throw new DomainException(DError.MONEY_INVALID);
        }
        if (description == null || description.trim().isEmpty()) {
            throw new DomainException(DError.PAYMENT_DESCRIPTION_INVALID);
        }
    }

    public void markAsSuccess(String transactionRef) {
        if (this.status != PaymentStatus.PROCESSING) {
            throw new DomainException(DError.PAYMENT_STATUS_INVALID);
        }
        this.status = PaymentStatus.SUCCESS;
        this.transactionRef = transactionRef;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsCancelled() {
        if (this.status == PaymentStatus.SUCCESS) {
            throw new DomainException(DError.PAYMENT_STATUS_INVALID);
        }
        this.status = PaymentStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }
}
