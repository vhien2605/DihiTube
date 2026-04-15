package com.pm.metadataservice.infra.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoEventPublisher {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishVideoCreated(Object event) {
        kafkaTemplate.send("video.created", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Sent message to topic video.created");
                    } else {
                        System.out.println("Failed to send message: " + ex.getMessage());
                    }
                });
    }
}