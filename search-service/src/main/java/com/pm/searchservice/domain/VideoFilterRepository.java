package com.pm.searchservice.domain;

import java.util.List;

public interface VideoFilterRepository {
    List<VideoDocument> searchByTitle(String keyword, int page, int size);
    List<VideoDocument> autoComplete(String keyword, int size);
    List<VideoDocument> filterByGenres(List<String> genres, int page, int size);
    List<VideoDocument> sortByViews(SortStatus sort, int page, int size);
}
