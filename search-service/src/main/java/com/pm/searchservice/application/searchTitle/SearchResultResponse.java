package com.pm.searchservice.application.searchTitle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchResultResponse {
    private String id;
    private String title;
    private String description;
    private List<String> genre;
    private Long views;
    private String thumbnailUrl;
}
