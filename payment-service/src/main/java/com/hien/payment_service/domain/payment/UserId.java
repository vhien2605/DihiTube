package com.hien.payment_service.domain.payment;

import com.fasterxml.uuid.Generators;
import com.hien.payment_service.domain.exception.DError;
import com.hien.payment_service.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class UserId {
    private final UUID value;

    private UserId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.ID_INVALID);
        }
        this.value = value;
    }

    public static UserId generate() {
        //uuidv6
        return new UserId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }

    public static UserId of(String value) {
        return new UserId(UUID.fromString(value));
    }
}
