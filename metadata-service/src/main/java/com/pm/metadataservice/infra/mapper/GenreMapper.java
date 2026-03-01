package com.pm.metadataservice.infra.mapper;

import com.pm.metadataservice.domain.Genre;
import com.pm.metadataservice.domain.GenreId;
import com.pm.metadataservice.infra.model.JpaGenre;

import java.util.List;

public class GenreMapper {

    public static Genre toDomain(JpaGenre jpa) {
        return new Genre(
                GenreId.of(jpa.getId()),
                jpa.getName()
        );
    }

    public static JpaGenre toJpa(Genre genre) {
        JpaGenre jpa = new JpaGenre();
        jpa.setId(genre.getId().toString());
        jpa.setName(genre.getName());
        return jpa;
    }

    public static List<JpaGenre> toJpa(List<Genre> genres) {
        if (genres == null) return List.of();

        return genres.stream()
                .map(GenreMapper::toJpa)
                .toList();
    }
}
