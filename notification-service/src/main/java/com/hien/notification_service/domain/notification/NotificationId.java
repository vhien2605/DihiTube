package com.hien.notification_service.domain.notification;

import com.fasterxml.uuid.Generators;
import com.hien.notification_service.domain.exception.DError;
import com.hien.notification_service.domain.exception.DomainException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class NotificationId {
    private final UUID value;

    private NotificationId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.NOTIFICATION_ID_INVALID);
        }
        this.value = value;
    }

    public static NotificationId generate() {
        //uuidv6
        return new NotificationId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static NotificationId of(UUID value) {
        return new NotificationId(value);
    }

    public static NotificationId of(String value) {
        return new NotificationId(UUID.fromString(value));
    }
}
