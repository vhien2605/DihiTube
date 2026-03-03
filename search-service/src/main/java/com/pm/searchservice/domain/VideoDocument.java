package com.pm.searchservice.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VideoDocument {
    private String id;
    private String title;
    private String description;
    private List<String> genre;
    private Long views;
    private String thumbnailUrl;

    public VideoDocument(String id, String title, String description, List<String> genre, Long views, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.views = views;
        this.thumbnailUrl = thumbnailUrl;
    }
}
