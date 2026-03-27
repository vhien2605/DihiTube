package com.pm.searchservice.application.filerGenres;

import com.pm.searchservice.application.SearchResultResponse;
import com.pm.searchservice.domain.VideoDocument;
import com.pm.searchservice.infra.VideoFilterImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterGenresUseCase {
    private final VideoFilterImpl filterImpl;

    public List<SearchResultResponse> filterByGenres(FilterGenresCommand req) {
        List<VideoDocument> videos = filterImpl.filterByGenres(req.getGenres(), req.getPage(), req.getPageSize());

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
