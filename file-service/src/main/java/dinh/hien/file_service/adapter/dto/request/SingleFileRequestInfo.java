package dinh.hien.file_service.adapter.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleFileRequestInfo {
    private String fileName;
    private String contentType;
    private Long size;
}
