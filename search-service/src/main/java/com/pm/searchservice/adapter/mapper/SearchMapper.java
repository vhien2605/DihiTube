package com.pm.searchservice.adapter.mapper;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.adapter.dto.response.search.SearchResponseDto;
import com.pm.searchservice.application.createIndex.CreateVideoDocumentCommand;
import com.pm.searchservice.application.searchTitle.SearchResultResponse;

import java.util.List;
import java.util.stream.Collectors;

public class SearchMapper {
    public static CreateVideoDocumentCommand toCreateVideoCommand(CreateVideoDocumentRequest req) {
        if (req == null) return null;

        return CreateVideoDocumentCommand.builder()
                .id(req.getId())
                .title(req.getTitle())
                .description(req.getDescription())
                .genre(req.getGenre())
                .views(req.getViews())
                .build();
    }

    public static SearchResponseDto toSearchResponse(SearchResultResponse res) {
        if (res == null) return null;

        return SearchResponseDto.builder()
                .id(res.getId())
                .title(res.getTitle())
                .description(res.getDescription())
                .genre(res.getGenre())
                .views(res.getViews())
                .thumbnailUrl(res.getThumbnailUrl())
                .build();
    }

    public static List<SearchResponseDto> toSearchResponse(List<SearchResultResponse> res) {
        if (res == null) return null;

        return res.stream().map(SearchMapper::toSearchResponse).collect(Collectors.toList());
    }
}
