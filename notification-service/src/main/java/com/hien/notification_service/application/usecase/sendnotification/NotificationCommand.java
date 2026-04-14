package com.hien.notification_service.application.usecase.sendnotification;

import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCommand {
    private String channel;
    private String recipient;
    private String title;
    private String content;
}
