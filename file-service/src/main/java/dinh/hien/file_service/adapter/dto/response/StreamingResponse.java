package dinh.hien.file_service.adapter.dto.response;

import dinh.hien.file_service.domain.file.FileType;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StreamingResponse {
    private String fileName;
    private long size;
    private String storageKey;
    private FileType type;
    private Boolean isPrivateFile;
}
