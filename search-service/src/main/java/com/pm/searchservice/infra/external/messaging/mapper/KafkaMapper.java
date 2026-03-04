package com.pm.searchservice.infra.external.messaging.mapper;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.infra.external.messaging.event.VideoCreatedEvent;

public class KafkaMapper {
    public static VideoDocument toVideoDocument(VideoCreatedEvent event) {
        return VideoDocument.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .genre(event.getGenre())
                .views(event.getViews())
                .thumbnailUrl(event.getThumbnailUrl())
                .build();
    }
}
