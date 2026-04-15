package dinh.hien.file_service.adapter.api;

import dinh.hien.file_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.file_service.application.usecase.streaming.StreamingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stream")
public class StreamingController {
    private final StreamingUseCase streamingUseCase;

    @GetMapping
    public ResponseEntity<ApiSuccessResponse<String>> metadata(
            @RequestParam UUID id) {
        String presignedUrl = streamingUseCase.streaming(id);
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Get streaming presigned url successfully")
                        .data(presignedUrl)
                        .build();
        return ResponseEntity.ok(response);
    }
}
