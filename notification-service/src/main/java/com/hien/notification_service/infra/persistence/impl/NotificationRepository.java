package com.hien.notification_service.infra.persistence.impl;

import com.hien.notification_service.domain.notification.INotiRepository;
import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.infra.mapper.NotificationMapper;
import com.hien.notification_service.infra.persistence.repository.MongoNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NotificationRepository implements INotiRepository {
    private final MongoNotificationRepository mongoNotificationRepository;

    @Override
    public void save(Notification notification) {
        mongoNotificationRepository.save(NotificationMapper.toDocument(notification));
    }
}
