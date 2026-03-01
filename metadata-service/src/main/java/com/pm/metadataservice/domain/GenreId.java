package com.pm.metadataservice.domain;

import com.fasterxml.uuid.Generators;
import lombok.Getter;

import java.util.UUID;

@Getter
public class GenreId {
    private final UUID value;

    public GenreId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("GenreId must not be null");
        }
        this.value = value;
    }

    public static GenreId generate() {
        //uuidv6
        return new GenreId(Generators.timeBasedReorderedGenerator().generate());
    }
    public static GenreId of(UUID value) {
        return new GenreId(value);
    }

    public static GenreId of(String value) {
        return new GenreId(UUID.fromString(value));
    }
}
