package com.hien.notification_service.infra.service.strategy;

import com.google.firebase.ErrorCode;
import com.hien.notification_service.domain.notification.Notification;
import com.hien.notification_service.domain.notification.NotificationType;
import com.hien.notification_service.infra.exception.InfraError;
import com.hien.notification_service.infra.exception.InfraException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationStrategy {
    private final JavaMailSender emailSender;

    @Value("${mail.sender.username}")
    private String username;

    @Override
    public void sendNotification(Notification notification) {
        log.info("Sending EMAIL notification to user: {}, title: {}",
                notification.getRecipientId(), notification.getTitle());
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(notification.getRecipientId());
            helper.setSubject(notification.getTitle());
            helper.setText(notification.getContent());
            emailSender.send(message);
        } catch (MessagingException e) {
            log.error("error", e);
            throw new InfraException(InfraError.MAIL_SERVER_ERROR);
        }
    }

    @Override
    public NotificationType supports() {
        return NotificationType.EMAIL;
    }
}
