package dinh.hien.file_service.adapter.api;


import dinh.hien.file_service.adapter.dto.request.FileUploadRequestDTO;
import dinh.hien.file_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.file_service.adapter.dto.response.UploadResponseDTO;
import dinh.hien.file_service.adapter.mapper.FileMapper;
import dinh.hien.file_service.application.usecase.upload.UploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileController {
    private final UploadUseCase uploadUseCase;

    @PostMapping("/upload")
    public ResponseEntity<ApiSuccessResponse<UploadResponseDTO>> upload(
            @RequestBody FileUploadRequestDTO fileUploadRequestDTOs
    ) {
        var result = uploadUseCase.upload(FileMapper.toUploadCommand(fileUploadRequestDTOs));
        ApiSuccessResponse<UploadResponseDTO> response =
                ApiSuccessResponse.<UploadResponseDTO>builder()
                        .message("file upload presign url returned")
                        .data(FileMapper.toUploadResponseDTO(result))
                        .build();
        return ResponseEntity.ok(response);
    }
}
