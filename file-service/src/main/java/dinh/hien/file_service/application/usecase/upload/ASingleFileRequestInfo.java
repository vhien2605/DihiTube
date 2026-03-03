package dinh.hien.file_service.application.usecase.upload;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ASingleFileRequestInfo {
    private String fileName;
    private String contentType;
    private Long size;
}
