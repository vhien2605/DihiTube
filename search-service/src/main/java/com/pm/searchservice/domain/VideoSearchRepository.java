package com.pm.searchservice.domain;

import java.util.List;

public interface VideoSearchRepository {
    List<VideoDocument> searchByTitle(String keyword, int page, int size);
    List<VideoDocument> autoComplete(String keyword, int size);
    List<VideoDocument> filterByGenres(List<String> genres, int page, int size);
}
