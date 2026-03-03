package dinh.hien.file_service.adapter.api;


import dinh.hien.file_service.adapter.dto.request.DownloadRequestDTO;
import dinh.hien.file_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.file_service.adapter.dto.response.DownloadResponseDTO;
import dinh.hien.file_service.application.usecase.download.DownloadCommand;
import dinh.hien.file_service.application.usecase.download.DownloadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class InternalFileController {
    private final DownloadUseCase downloadUseCase;

    @GetMapping("/download")
    public ResponseEntity<ApiSuccessResponse<DownloadResponseDTO>> download(
            @RequestBody DownloadRequestDTO dto
    ) {
        var result = downloadUseCase.download(DownloadCommand.builder().storageKey(dto.getStorageKey()).build());
        ApiSuccessResponse<DownloadResponseDTO> response =
                ApiSuccessResponse.<DownloadResponseDTO>builder()
                        .message("download link info created")
                        .data(DownloadResponseDTO.builder().downloadLink(result.getDownloadLink()).build())
                        .build();
        return ResponseEntity.ok(response);
    }
}
