package dinh.hien.file_service.application.usecase.metadata;

import lombok.*;

import java.time.Instant;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationFileMetaData {
    private String fileName;
    private Long size;
    private String storageKey;
    private String contentType;
    private Instant createdAt;
}
