package com.pm.metadataservice.infra.persistence;

import com.pm.metadataservice.domain.IVideoRepository;
import com.pm.metadataservice.domain.Video;
import com.pm.metadataservice.domain.VideoId;
import com.pm.metadataservice.infra.mapper.VideoMapper;
import com.pm.metadataservice.infra.model.JpaGenre;
import com.pm.metadataservice.infra.model.JpaVideo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VideoRepositoryImpl implements IVideoRepository {
    private final JpaVideoRepository jpaVideoRepository;
    private final JpaGenreRepository jpaGenreRepository;

    @Override
    public Video save(Video video) {
        JpaVideo jpa = VideoMapper.toJpa(video);

        List<String> genreIds = video.getGenres().stream()
                .map(g -> g.getId().getValue().toString())
                .toList();

        List<JpaGenre> managedGenres = jpaGenreRepository.findAllById(genreIds);

        jpa.setGenres(managedGenres);

        return VideoMapper.toDomain(jpaVideoRepository.save(jpa));
    }

    @Override
    public Optional<Video> findById(VideoId id) {
        return jpaVideoRepository.findById(String.valueOf(id.getValue()))
                .map(VideoMapper::toDomain);
    }
}
