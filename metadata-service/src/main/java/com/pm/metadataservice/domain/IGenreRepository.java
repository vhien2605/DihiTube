package com.pm.metadataservice.domain;

import java.util.List;
import java.util.Optional;

public interface IGenreRepository {
    Optional<Genre> findById(GenreId id);
    List<Genre> findAllById(List<String> ids);
}
