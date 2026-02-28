package com.pm.metadataservice.adapter.api;

import com.pm.metadataservice.adapter.dto.request.CreateVideoRequest;
import com.pm.metadataservice.adapter.dto.response.ApiSuccessResponse;
import com.pm.metadataservice.adapter.mapper.VideoMapper;
import com.pm.metadataservice.application.CreateVideoCommand;
import com.pm.metadataservice.application.VideoApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {
    private final VideoApplicationService service;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<String>> create(@RequestBody @Valid CreateVideoRequest request) {
        CreateVideoCommand cmd = VideoMapper.toCreateVideoCommand(request);
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Create Video successfully!")
                        .data(service.createVideo(cmd))
                        .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/view")
    public ResponseEntity<ApiSuccessResponse<String>> view(@PathVariable String id) {
        service.increaseView(UUID.fromString(id));
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Increase view successfully!")
                        .build();
        return ResponseEntity.ok(response);
    }
}
