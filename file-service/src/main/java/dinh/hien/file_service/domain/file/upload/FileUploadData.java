package dinh.hien.file_service.domain.file.upload;


import dinh.hien.file_service.domain.file.FileMetaData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileUploadData {
    private FileMetaData metaData;
    private String uploadLink;
}
