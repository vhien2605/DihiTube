package com.hien.notification_service.domain.notification;

import com.hien.notification_service.domain.exception.DError;
import com.hien.notification_service.domain.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class Notification {
    private NotificationId id;
    private String recipientId;

    private NotificationType type;
    private String title;
    private String content;

    private NotificationStatus status;

    private Instant createdAt;


    public static Notification create(
            String recipientId,
            NotificationType type,
            String title,
            String content
    ) {
        // Validate recipientId
        if (recipientId == null) {
            throw new DomainException(DError.USER_ID_INVALID);
        }

        // Validate type
        if (type == null) {
            throw new DomainException(DError.NOTIFICATION_TYPE_INVALID);
        }

        // Validate title
        if (title == null || title.trim().isEmpty()) {
            throw new DomainException(DError.NOTIFICATION_TITLE_INVALID);
        }

        // Validate content
        if (content == null || content.trim().isEmpty()) {
            throw new DomainException(DError.NOTIFICATION_CONTENT_INVALID);
        }
        return new Notification(
                NotificationId.generate(),
                recipientId,
                type,
                title,
                content,
                NotificationStatus.UNREAD,
                Instant.now()
        );
    }

    public static Notification of(
            NotificationId id,
            String recipientId,
            NotificationType type,
            String title,
            String content,
            NotificationStatus status,
            Instant createdAt
    ) {
        // Validate id
        if (id == null) {
            throw new DomainException(DError.NOTIFICATION_ID_INVALID);
        }

        // Validate recipientId
        if (recipientId == null) {
            throw new DomainException(DError.USER_ID_INVALID);
        }

        // Validate type
        if (type == null) {
            throw new DomainException(DError.NOTIFICATION_TYPE_INVALID);
        }

        // Validate title
        if (title == null || title.trim().isEmpty()) {
            throw new DomainException(DError.NOTIFICATION_TITLE_INVALID);
        }

        // Validate content
        if (content == null || content.trim().isEmpty()) {
            throw new DomainException(DError.NOTIFICATION_CONTENT_INVALID);
        }
        // Validate status
        if (status == null) {
            throw new DomainException(DError.NOTIFICATION_STATUS_INVALID);
        }
        // Validate createdAt
        if (createdAt == null) {
            throw new DomainException(DError.NOTIFICATION_CREATED_AT_INVALID);
        }
        return new Notification(id, recipientId, type, title, content, status, createdAt);
    }
}
