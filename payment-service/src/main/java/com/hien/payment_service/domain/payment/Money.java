package com.hien.payment_service.domain.payment;

import com.hien.payment_service.domain.exception.DError;
import com.hien.payment_service.domain.exception.DomainException;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        validate(amount, currency);
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(BigDecimal amount, Currency currency) {
        return new Money(amount, currency);
    }

    public static Money of(Double amount, Currency currency) {
        return new Money(BigDecimal.valueOf(amount), currency);
    }

    private static void validate(BigDecimal amount, Currency currency) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainException(DError.MONEY_INVALID);
        }
        if (currency == null) {
            throw new DomainException(DError.MONEY_INVALID);
        }
    }

    public Money add(Money other) {
        if (this.currency != other.currency) {
            throw new DomainException(DError.MONEY_INVALID);
        }
        return new Money(this.amount.add(other.amount), this.currency);
    }

    public Money subtract(Money other) {
        if (this.currency != other.currency) {
            throw new DomainException(DError.MONEY_INVALID);
        }
        return new Money(this.amount.subtract(other.amount), this.currency);
    }
}
