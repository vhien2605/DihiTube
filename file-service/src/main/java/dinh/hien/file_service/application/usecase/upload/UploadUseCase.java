package dinh.hien.file_service.application.usecase.upload;

import dinh.hien.file_service.domain.file.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UploadUseCase {
    private final IFileService fileService;

    public UploadResult upload(UploadCommand command) {
        var domainResult = fileService.uploadPreparation(command);
        List<ASingleFileResponse> responses = domainResult.getData().stream()
                .map(d -> ASingleFileResponse.builder()
                        .size(d.getMetaData().getSize().getValue())
                        .createdAt(d.getMetaData().getCreatedAt())
                        .fileName(d.getMetaData().getFileName())
                        .contentType(d.getMetaData().getContentType())
                        .storageKey(d.getMetaData().getStorageKey())
                        .presignUrl(d.getUploadLink())
                        .isPrivateFile(d.getMetaData().getIsPrivateFile())
                        .build())
                .toList();
        return UploadResult.builder().singleFileResponseList(responses).build();
    }
}
