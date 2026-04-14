package com.hien.notification_service.infra.persistence.repository;

import com.hien.notification_service.infra.model.NotificationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MongoNotificationRepository extends MongoRepository<NotificationDocument, String> {
    
}
