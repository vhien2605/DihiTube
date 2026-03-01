package dinh.hien.file_service.adapter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadRequestDTO {
    private String fileName;
    private String contentType;
}
