package com.hien.notification_service.application.service;

import com.hien.notification_service.domain.notification.Notification;

public interface ISenderService {
    void sendNotification(Notification notification);
}
