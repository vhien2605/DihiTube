package com.hien.notification_service.adapter.mapper;

import com.hien.notification_service.adapter.dto.request.NotificationRequestDTO;
import com.hien.notification_service.application.usecase.sendnotification.NotificationCommand;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NotificationMapper {
    public static NotificationCommand toCommand(NotificationRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        return NotificationCommand.builder()
                .channel(requestDTO.getChannel())
                .recipient(requestDTO.getRecipient())
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .build();
    }

    public static NotificationRequestDTO toRequestDTO(NotificationCommand command) {
        if (command == null) {
            return null;
        }
        return NotificationRequestDTO.builder()
                .channel(command.getChannel())
                .recipient(command.getRecipient())
                .title(command.getTitle())
                .content(command.getContent())
                .build();


    }
}
