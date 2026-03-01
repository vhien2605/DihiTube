package com.pm.metadataservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genre {
    private final GenreId id;
    private String name;

    public Genre(GenreId id,  String name) {
        this.id = id;
        this.name = name;
    }
}
