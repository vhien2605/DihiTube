package com.pm.searchservice.adapter.api;

import com.pm.searchservice.adapter.dto.request.CreateVideoDocumentRequest;
import com.pm.searchservice.adapter.mapper.SearchMapper;
import com.pm.searchservice.application.CreateIndexUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {
    private final CreateIndexUseCase service;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateVideoDocumentRequest request) {
        service.createDocument(SearchMapper.toCreateVideoCommand(request));
        return ResponseEntity.ok("da tao document thanh cong");
    }
}
