package com.hien.notification_service.infra.service;

import com.hien.notification_service.application.service.ISenderService;
import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.infra.service.strategy.NotificationStrategy;
import com.hien.notification_service.infra.service.strategy.NotificationStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements ISenderService {
    private final NotificationStrategyFactory strategyFactory;

    @Override
    @Async
    public void sendNotification(Notification notification) {
        log.info("------------------------Noti run on Thread: {}", Thread.currentThread().getName());
        // Select strategy based on notification type
        NotificationStrategy strategy = strategyFactory.getStrategy(notification.getType());
        // Execute strategy
        strategy.sendNotification(notification);
        log.info("Notification sent successfully");
    }
}
