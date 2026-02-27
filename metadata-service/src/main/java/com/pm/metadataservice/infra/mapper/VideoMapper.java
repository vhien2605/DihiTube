package com.pm.metadataservice.infra.mapper;

import com.pm.metadataservice.domain.Video;
import com.pm.metadataservice.domain.VideoId;
import com.pm.metadataservice.infra.model.JpaVideo;

import java.util.List;

public class VideoMapper {
    public static JpaVideo toJpa(Video video) {
        JpaVideo jpa = new JpaVideo();
        jpa.setId(String.valueOf(video.getId().getValue()));
        jpa.setTitle(video.getTitle());
        jpa.setDescription(video.getDescription());
        jpa.setViews(video.getViews());
        jpa.setStorageKey(video.getStorageKey());
        jpa.setStatus(video.getStatus().name());
        return jpa;
    }

    public static Video toDomain(JpaVideo jpa) {
        return new Video(
                VideoId.of(jpa.getId()),
                jpa.getTitle(),
                jpa.getDescription(),
                List.of(),
                jpa.getThumbnailUrl(),
                jpa.getDuration(),
                null,
                jpa.getStorageKey()
        );
    }
}
