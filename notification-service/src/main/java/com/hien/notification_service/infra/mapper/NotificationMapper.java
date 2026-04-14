package com.hien.notification_service.infra.mapper;

import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.domain.notification.NotificationId;
import com.hien.notification_service.domain.notification.NotificationStatus;
import com.hien.notification_service.domain.notification.NotificationType;
import com.hien.notification_service.infra.model.NotificationDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
public class NotificationMapper {
    private NotificationMapper() {

    }

    public static NotificationDocument toDocument(Notification notification) {
        if (notification == null) {
            return null;
        }

        return NotificationDocument.builder()
                .id(notification.getId().getValue().toString())
                .recipientId(notification.getRecipientId())
                .type(notification.getType().name())
                .title(notification.getTitle())
                .content(notification.getContent())
                .status(notification.getStatus().name())
                .createdAt(notification.getCreatedAt())
                .build();
    }


    public static Notification toDomain(NotificationDocument document) {
        if (document == null) {
            return null;
        }

        return Notification.of(
                NotificationId.of(document.getId()),
                document.getRecipientId(),
                NotificationType.valueOf(document.getType()),
                document.getTitle(),
                document.getContent(),
                NotificationStatus.valueOf(document.getStatus()),
                document.getCreatedAt()
        );
    }
}
