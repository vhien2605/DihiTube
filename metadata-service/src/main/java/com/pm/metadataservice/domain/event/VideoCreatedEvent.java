package com.pm.metadataservice.domain.event;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoCreatedEvent {
    private String id;
    private String title;
    private String description;
    private List<String> genre;
    private Long views;
    private String thumbnailUrl;
}