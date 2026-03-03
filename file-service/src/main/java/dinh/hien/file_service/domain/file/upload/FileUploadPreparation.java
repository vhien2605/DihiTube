package dinh.hien.file_service.domain.file.upload;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class FileUploadPreparation {
    private List<FileUploadData> data;
}
