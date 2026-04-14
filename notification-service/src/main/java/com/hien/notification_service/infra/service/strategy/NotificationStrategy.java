package com.hien.notification_service.infra.service.strategy;

import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.domain.notification.NotificationType;


public interface NotificationStrategy {
    void sendNotification(Notification notification);

    NotificationType supports();
}

