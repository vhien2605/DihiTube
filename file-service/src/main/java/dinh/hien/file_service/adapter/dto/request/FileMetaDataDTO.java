package dinh.hien.file_service.adapter.dto.request;


import lombok.*;

import java.time.Instant;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileMetaDataDTO {
    private String fileName;
    private Long size;
    private String storageKey;
    private String contentType;
    private Instant createdAt;
}
