package com.pm.metadataservice.application;

import com.pm.metadataservice.domain.Genre;
import com.pm.metadataservice.domain.Video;
import com.pm.metadataservice.domain.VideoId;
import com.pm.metadataservice.domain.event.VideoCreatedEvent;
import com.pm.metadataservice.infra.persistence.GenreRepositoryImpl;
import com.pm.metadataservice.infra.persistence.VideoRepositoryImpl;
import com.pm.metadataservice.infra.publisher.VideoEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoApplicationService {
    private final VideoRepositoryImpl videoRepository;
    private final GenreRepositoryImpl genreRepository;
    private final VideoEventPublisher publisher;

    public String createVideo(CreateVideoCommand cmd) {
        List<Genre> genres = genreRepository.findAllById(cmd.getGenreIds());

        if (genres.size() != cmd.getGenreIds().size()) {
            throw new RuntimeException("Genre không tồn tại");
        }

        Video video = new Video(
                VideoId.of(UUID.randomUUID().toString()),
                cmd.title,
                cmd.description,
                genres,
                cmd.thumbnailUrl,
                cmd.duration,
                cmd.releaseDate,
                cmd.storageKey,
                cmd.isFreeResources
        );

        videoRepository.save(video);

        List<String> kafkaGenres = genres.stream().map(Genre::getName).toList();

        VideoCreatedEvent event = VideoCreatedEvent.builder()
                .id(video.getId().getValue().toString())
                .title(video.getTitle())
                .description(video.getDescription())
                .thumbnailUrl(video.getThumbnailUrl())
                .genre(kafkaGenres)
                .views(video.getViews())
                .build();
        publisher.publishVideoCreated(event);

        return video.getId().getValue().toString();
    }

    public void increaseView(UUID videoId) {
        Video video = videoRepository.findById(VideoId.of(videoId))
                .orElseThrow();

        video.increaseView();
        videoRepository.save(video);
    }
}
