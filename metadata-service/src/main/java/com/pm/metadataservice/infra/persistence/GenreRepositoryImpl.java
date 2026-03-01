package com.pm.metadataservice.infra.persistence;

import com.pm.metadataservice.domain.Genre;
import com.pm.metadataservice.domain.GenreId;
import com.pm.metadataservice.domain.IGenreRepository;
import com.pm.metadataservice.infra.mapper.GenreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements IGenreRepository {
    private final JpaGenreRepository jpaGenreRepository;

    @Override
    public Optional<Genre> findById(GenreId id) {
        return jpaGenreRepository.findById(String.valueOf(id.getValue()))
                .map(GenreMapper::toDomain);
    }

    @Override
    public List<Genre> findAllById(List<String> ids) {
        return jpaGenreRepository.findAllById(ids).stream()
                .map(GenreMapper::toDomain).collect(Collectors.toList());
    }
}
