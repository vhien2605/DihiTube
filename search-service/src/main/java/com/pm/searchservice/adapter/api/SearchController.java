package com.pm.searchservice.adapter.api;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.adapter.dto.request.SearchTitleRequest;
import com.pm.searchservice.adapter.dto.response.ApiSuccessResponse;
import com.pm.searchservice.adapter.dto.response.search.SearchResponseDto;
import com.pm.searchservice.adapter.mapper.SearchMapper;
import com.pm.searchservice.application.createIndex.CreateIndexUseCase;
import com.pm.searchservice.application.searchTitle.SearchResultResponse;
import com.pm.searchservice.application.searchTitle.SearchTitleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final CreateIndexUseCase service;
    private final SearchTitleUseCase searchTitleUseCase;

    @PostMapping("/create")
    public ResponseEntity<ApiSuccessResponse<String>> create(@RequestBody CreateVideoDocumentRequest request) {
        service.createDocument(SearchMapper.toCreateVideoCommand(request));
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Create document successfully!")
                        .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/title")
    public ResponseEntity<ApiSuccessResponse<List<SearchResponseDto>>> search(@RequestBody SearchTitleRequest request) {
        List<SearchResultResponse> response = searchTitleUseCase.searchByTitle(request);

        ApiSuccessResponse<List<SearchResponseDto>> res =
                ApiSuccessResponse.<List<SearchResponseDto>>builder()
                        .message("Search by title successfully!")
                        .data(SearchMapper.toSearchResponse(response))
                        .build();

        return ResponseEntity.ok(res);
    }
}
