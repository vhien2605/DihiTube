package com.pm.metadataservice.infra.persistence;

import com.pm.metadataservice.domain.Genre;
import com.pm.metadataservice.infra.model.JpaGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaGenreRepository extends JpaRepository<JpaGenre, String> {
}
