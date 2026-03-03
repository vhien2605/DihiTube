package dinh.hien.file_service.domain.file;

import dinh.hien.file_service.application.usecase.download.DownloadCommand;
import dinh.hien.file_service.application.usecase.upload.UploadCommand;
import dinh.hien.file_service.domain.file.download.FileDownloadPreparation;
import dinh.hien.file_service.domain.file.upload.FileUploadPreparation;

public interface IFileService {
    FileUploadPreparation uploadPreparation(UploadCommand command);

    FileDownloadPreparation downloadPreparation(DownloadCommand command);
}
