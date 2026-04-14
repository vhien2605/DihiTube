package com.hien.notification_service.infra.service.strategy;

import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.domain.notification.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsNotificationService implements NotificationStrategy {

    @Override
    public void sendNotification(Notification notification) {
        log.info("Sending SMS notification to user: {}, content: {}",
                notification.getRecipientId(), notification.getContent());
    }

    @Override
    public NotificationType supports() {
        return NotificationType.SMS;
    }
}
