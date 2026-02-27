package com.pm.metadataservice.application;

import com.pm.metadataservice.domain.Video;
import com.pm.metadataservice.domain.VideoId;
import com.pm.metadataservice.infra.persistence.VideoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoApplicationService {
    private final VideoRepositoryImpl videoRepository;

    public String createVideo(CreateVideoCommand cmd) {
        Video video = new Video(
                VideoId.of(UUID.randomUUID().toString()),
                cmd.title,
                cmd.description,
                cmd.genres,
                cmd.thumbnailUrl,
                cmd.duration,
                cmd.releaseDate,
                cmd.storageKey
        );

        videoRepository.save(video);
        return video.getId().getValue().toString();
    }

    public void increaseView(UUID videoId) {
        Video video = videoRepository.findById(VideoId.of(videoId))
                .orElseThrow();

        video.increaseView();
        videoRepository.save(video);
    }
}
