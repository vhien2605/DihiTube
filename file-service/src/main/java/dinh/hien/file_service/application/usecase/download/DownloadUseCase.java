package dinh.hien.file_service.application.usecase.download;


import dinh.hien.file_service.domain.file.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DownloadUseCase {
    private final IFileService fileService;

    public DownloadResult download(DownloadCommand downloadCommand) {
        var domainResult = fileService.downloadPreparation(downloadCommand);
        return DownloadResult.builder().downloadLink(domainResult.getStorageKey()).build();
    }
}
