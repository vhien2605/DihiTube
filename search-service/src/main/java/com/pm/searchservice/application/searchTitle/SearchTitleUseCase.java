package com.pm.searchservice.application.searchTitle;

import com.pm.searchservice.application.SearchResultResponse;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.infra.VideoFilterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchTitleUseCase {
    private final VideoFilterImpl filterImpl;

    public List<SearchResultResponse> searchByTitle(SearchTitleCommand req) {
        List<VideoDocument> videos = filterImpl.searchByTitle(req.getKeyword(), req.getPage(), req.getPageSize());

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
        List<VideoDocument> videos = filterImpl.autoComplete(keyword, size);

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