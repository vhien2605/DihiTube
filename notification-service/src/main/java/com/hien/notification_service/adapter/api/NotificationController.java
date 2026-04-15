package com.hien.notification_service.adapter.api;


import com.hien.notification_service.adapter.dto.request.NotificationRequestDTO;
import com.hien.notification_service.adapter.dto.response.ApiSuccessResponse;
import com.hien.notification_service.adapter.mapper.NotificationMapper;
import com.hien.notification_service.application.usecase.sendnotification.AdminNotificationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/admin")
public class NotificationController {
    private final AdminNotificationUseCase adminNotificationUseCase;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<String>> postNotification(
            @RequestBody NotificationRequestDTO dto) {
        //post noti
        adminNotificationUseCase.sendNotification(
                NotificationMapper.toCommand(dto)
        );
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("post notification ok")
                        .build();
        return ResponseEntity.ok(response);
    }
}
