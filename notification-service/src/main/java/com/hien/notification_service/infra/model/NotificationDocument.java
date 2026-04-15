package com.hien.notification_service.infra.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notification")
public class NotificationDocument {
    @Id
    private String id;

    @Field("recipient_id")
    @Indexed
    private String recipientId;

    @Field("type")
    private String type;

    @Field("title")
    private String title;

    @Field("content")
    private String content;

    @Field("status")
    private String status;

    @Field("created_at")
    private Instant createdAt;
}
