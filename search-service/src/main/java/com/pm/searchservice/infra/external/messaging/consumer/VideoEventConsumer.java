package com.pm.searchservice.infra.external.messaging.consumer;

import com.pm.searchservice.infra.VideoCreateImpl;
import com.pm.searchservice.infra.external.messaging.event.VideoCreatedEvent;
import com.pm.searchservice.infra.external.messaging.mapper.KafkaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VideoEventConsumer {

    private final VideoCreateImpl service;

    @KafkaListener(topics = "video.created", groupId = "search-group-v2")
    public void handleVideoCreated(VideoCreatedEvent event) {
        System.out.println("START consume: " + event.getId());

        service.createDocument(KafkaMapper.toVideoDocument(event));

        System.out.println("END consume: " + event.getId());
    }
}