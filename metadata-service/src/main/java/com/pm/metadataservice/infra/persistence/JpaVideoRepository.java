package com.pm.metadataservice.infra.persistence;

import com.pm.metadataservice.domain.VideoId;
import com.pm.metadataservice.infra.model.JpaVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVideoRepository extends JpaRepository<JpaVideo, String> {
}
