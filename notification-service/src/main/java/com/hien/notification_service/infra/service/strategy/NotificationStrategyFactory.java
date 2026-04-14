package com.hien.notification_service.infra.service.strategy;

import com.hien.notification_service.domain.notification.NotificationType;
import com.hien.notification_service.domain.exception.DError;
import com.hien.notification_service.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
@Slf4j
public class NotificationStrategyFactory {
    private final Map<NotificationType, NotificationStrategy> strategies;

    public NotificationStrategyFactory(List<NotificationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(
                        NotificationStrategy::supports,
                        strategy -> strategy
                ));
    }

    public NotificationStrategy getStrategy(NotificationType type) {
        if (type == null) {
            throw new DomainException(DError.NOTIFICATION_TYPE_INVALID);
        }
        NotificationStrategy strategy = strategies.get(type);
        if (strategy == null) {
            log.error("No strategy found for notification type: {}", type);
            throw new DomainException(DError.NOTIFICATION_TYPE_INVALID);
        }
        return strategy;
    }
}

