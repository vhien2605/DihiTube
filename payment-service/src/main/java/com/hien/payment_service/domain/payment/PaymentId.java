package com.hien.payment_service.domain.payment;

import com.fasterxml.uuid.Generators;
import com.hien.payment_service.domain.exception.DError;
import com.hien.payment_service.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PaymentId {
    private final UUID value;

    private PaymentId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.PAYMENT_ID_INVALID);
        }
        this.value = value;
    }

    public static PaymentId generate() {
        //uuidv6
        return new PaymentId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static PaymentId of(UUID value) {
        return new PaymentId(value);
    }

    public static PaymentId of(String value) {
        return new PaymentId(UUID.fromString(value));
    }
}
