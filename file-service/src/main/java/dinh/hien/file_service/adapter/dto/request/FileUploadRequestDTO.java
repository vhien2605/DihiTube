package dinh.hien.file_service.adapter.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadRequestDTO {
    private List<SingleFileRequestInfo> files;
}
