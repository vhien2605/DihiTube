package com.pm.searchservice.application.searchTitle;

import com.pm.searchservice.adapter.dto.request.SearchTitleRequest;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.infra.VideoSearchImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchTitleUseCase {
    private final VideoSearchImpl videoSearch;

    public List<SearchResultResponse> searchByTitle(SearchTitleRequest req) {
        List<VideoDocument> videos = videoSearch.searchByTitle(req.getKeyword(), req.getPage(), req.getPageSize());

        return videos.stream()
                .map(video ->
                        SearchResultResponse.builder()
                                .id(video.getId())
                                .title(video.getTitle())
                                .description(video.getDescription())
                                .genre(video.getGenre())
                                .views(video.getViews())
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<SearchResultResponse> autoComplete(String keyword, int size) {
        List<VideoDocument> videos = videoSearch.autoComplete(keyword, size);

        return videos.stream()
                .map(video ->
                        SearchResultResponse.builder()
                                .id(video.getId())
                                .title(video.getTitle())
                                .description(video.getDescription())
                                .genre(video.getGenre())
                                .views(video.getViews())
                                .build()
                )
                .collect(Collectors.toList());
    }
}