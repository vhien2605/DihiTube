package com.pm.metadataservice.domain;

import java.util.Optional;

public interface IVideoRepository {
    Video save(Video video);
    Optional<Video> findById(VideoId id);
}
