package com.pm.metadataservice.application;

import com.pm.metadataservice.domain.Genre;
import com.pm.metadataservice.domain.Video;
import com.pm.metadataservice.domain.VideoId;
import com.pm.metadataservice.infra.persistence.GenreRepositoryImpl;
import com.pm.metadataservice.infra.persistence.VideoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoApplicationService {
    private final VideoRepositoryImpl videoRepository;
    private final GenreRepositoryImpl genreRepository;

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
        return video.getId().getValue().toString();
    }

    public void increaseView(UUID videoId) {
        Video video = videoRepository.findById(VideoId.of(videoId))
                .orElseThrow();

        video.increaseView();
        videoRepository.save(video);
    }
}
