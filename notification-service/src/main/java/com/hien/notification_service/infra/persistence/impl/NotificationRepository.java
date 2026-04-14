package com.hien.notification_service.infra.persistence.impl;

import com.hien.notification_service.domain.notification.INotiRepository;
import com.hien.notification_service.domain.notification.Notification;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationRepository implements INotiRepository {
    @Override
    public void save(Notification notification) {

    }
}
