package com.pm.searchservice.adapter.mapper;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.adapter.dto.request.FilterGenresRequest;
import com.pm.searchservice.adapter.dto.request.SearchTitleRequest;
import com.pm.searchservice.adapter.dto.request.SortViewsRequest;
import com.pm.searchservice.adapter.dto.response.search.SearchResponseDto;
import com.pm.searchservice.application.createIndex.CreateVideoDocumentCommand;
import com.pm.searchservice.application.SearchResultResponse;
import com.pm.searchservice.application.filerGenres.FilterGenresCommand;
import com.pm.searchservice.application.searchTitle.SearchTitleCommand;
import com.pm.searchservice.application.sortViews.SortViewsCommand;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
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

        return res.stream().map(Mapper::toSearchResponse).collect(Collectors.toList());
    }

    public static SearchTitleCommand toSearchTitleCommand(SearchTitleRequest req) {
        if (req == null) return null;

        return SearchTitleCommand.builder()
                .keyword(req.getKeyword())
                .page(req.getPage())
                .pageSize(req.getPageSize())
                .build();
    }

    public static FilterGenresCommand toFilterGenresCommand(FilterGenresRequest req) {
        if (req == null) return null;

        return FilterGenresCommand.builder()
                .genres(req.getGenres())
                .page(req.getPage())
                .pageSize(req.getPageSize())
                .build();
    }

    public static SortViewsCommand toSortViewsCommand(SortViewsRequest req) {
        if (req == null) return null;

        return SortViewsCommand.builder()
                .page(req.getPage())
                .pageSize(req.getPageSize())
                .sort(req.getSort())
                .build();
    }
}
