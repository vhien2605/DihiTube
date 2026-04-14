package com.hien.notification_service.adapter.dto.request;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    private String channel;
    private String recipient;
    private String title;
    private String content;
}
