package com.hien.notification_service.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    NOTIFICATION_ID_INVALID(1001, "Notification ID is invalid"),
    USER_ID_INVALID(1002, "User ID is invalid"),
    NOTIFICATION_TYPE_INVALID(1003, "Notification type is invalid"),
    NOTIFICATION_STATUS_INVALID(1004, "Notification status is invalid"),
    NOTIFICATION_TITLE_INVALID(1005, "Notification title is invalid or empty"),
    NOTIFICATION_CONTENT_INVALID(1006, "Notification content is invalid or empty"),
    NOTIFICATION_CREATED_AT_INVALID(1007, "Notification created at is invalid"),
    ;
    private final int code;
    private final String message;
}
