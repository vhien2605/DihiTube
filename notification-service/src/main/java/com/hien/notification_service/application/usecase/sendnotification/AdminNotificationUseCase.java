package com.hien.notification_service.application.usecase.sendnotification;


import com.hien.notification_service.application.service.ISenderService;
import com.hien.notification_service.domain.notification.INotiRepository;
import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.domain.notification.NotificationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminNotificationUseCase {
    private final INotiRepository notiRepository;
    private final ISenderService senderService;

    public void sendNotification(NotificationCommand command) {
        // build noti domain
        Notification notification = Notification.create(
                command.getRecipient(),
                NotificationType.valueOf(command.getChannel().toUpperCase()),
                command.getTitle(),
                command.getContent()
        );
        // persist
        notiRepository.save(notification);
        // send noti
        senderService.sendNotification(notification);
    }
}
