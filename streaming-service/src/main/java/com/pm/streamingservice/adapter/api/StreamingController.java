package com.pm.streamingservice.adapter.api;

import com.pm.streamingservice.adapter.dto.response.ApiSuccessResponse;
import com.pm.streamingservice.appilcation.StreamingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stream")
public class StreamingController {
    private final StreamingUseCase streamingUsecase;

    @GetMapping
    public ResponseEntity<ApiSuccessResponse<String>> metadata(
            @RequestParam UUID id) {
        String result = streamingUsecase.streaming(id);
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Get streaming presigned url successfully")
                        .data(result)
                        .build();
        return ResponseEntity.ok(response);
    }
}
