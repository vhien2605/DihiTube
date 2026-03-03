package com.pm.searchservice.application.sortViews;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.pm.searchservice.application.SearchResultResponse;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.infra.VideoFilterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SortViewsUseCase {
    private final VideoFilterImpl filterImpl;

    public List<SearchResultResponse> sortByViews(SortViewsCommand cmd) {
        List<VideoDocument> videos = filterImpl
                .sortByViews(cmd.getSort(), cmd.getPage(), cmd.getPageSize());

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
