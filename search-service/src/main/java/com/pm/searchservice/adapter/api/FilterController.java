package com.pm.searchservice.adapter.api;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.adapter.dto.request.FilterGenresRequest;
import com.pm.searchservice.adapter.dto.request.SearchTitleRequest;
import com.pm.searchservice.adapter.dto.request.SortViewsRequest;
import com.pm.searchservice.adapter.dto.response.ApiSuccessResponse;
import com.pm.searchservice.adapter.dto.response.search.SearchResponseDto;
import com.pm.searchservice.adapter.mapper.Mapper;
import com.pm.searchservice.application.createIndex.CreateIndexUseCase;
import com.pm.searchservice.application.SearchResultResponse;
import com.pm.searchservice.application.filerGenres.FilterGenresUseCase;
import com.pm.searchservice.application.searchTitle.SearchTitleUseCase;
import com.pm.searchservice.application.sortViews.SortViewsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class FilterController {
    private final CreateIndexUseCase service;
    private final SearchTitleUseCase searchTitleUseCase;
    private final FilterGenresUseCase filterGenresUseCase;
    private final SortViewsUseCase sortViewsUseCase;

    @PostMapping("/create")
    public ResponseEntity<ApiSuccessResponse<String>> create(@RequestBody CreateVideoDocumentRequest request) {
        service.createDocument(Mapper.toCreateVideoCommand(request));
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Create document successfully!")
                        .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/title")
    public ResponseEntity<ApiSuccessResponse<List<SearchResponseDto>>> search(@RequestBody SearchTitleRequest request) {
        List<SearchResultResponse> response = searchTitleUseCase.searchByTitle(Mapper.toSearchTitleCommand(request));

        ApiSuccessResponse<List<SearchResponseDto>> res =
                ApiSuccessResponse.<List<SearchResponseDto>>builder()
                        .message("Search by title successfully!")
                        .data(Mapper.toSearchResponse(response))
                        .build();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/autocomplete")
    public ResponseEntity<ApiSuccessResponse<List<SearchResponseDto>>> search(@RequestParam String keyword,
                                                                              @RequestParam(defaultValue = "10") int size) {
        List<SearchResultResponse> response = searchTitleUseCase.autoComplete(keyword, size);

        ApiSuccessResponse<List<SearchResponseDto>> res =
                ApiSuccessResponse.<List<SearchResponseDto>>builder()
                        .message("Search by title successfully!")
                        .data(Mapper.toSearchResponse(response))
                        .build();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/genres")
    public ResponseEntity<ApiSuccessResponse<List<SearchResponseDto>>> filterByGenres(@RequestBody FilterGenresRequest request) {
        List<SearchResultResponse> response = filterGenresUseCase.filterByGenres(Mapper.toFilterGenresCommand(request));

        ApiSuccessResponse<List<SearchResponseDto>> res =
                ApiSuccessResponse.<List<SearchResponseDto>>builder()
                        .message("Filter by genres successfully!")
                        .data(Mapper.toSearchResponse(response))
                        .build();

        return ResponseEntity.ok(res);
    }

    @GetMapping("/views")
    public ResponseEntity<ApiSuccessResponse<List<SearchResponseDto>>> sortByViews(@RequestBody SortViewsRequest request) {
        List<SearchResultResponse> response = sortViewsUseCase.sortByViews(Mapper.toSortViewsCommand(request));

        ApiSuccessResponse<List<SearchResponseDto>> res =
                ApiSuccessResponse.<List<SearchResponseDto>>builder()
                        .message("Sort by views successfully!")
                        .data(Mapper.toSearchResponse(response))
                        .build();

        return ResponseEntity.ok(res);
    }
}
