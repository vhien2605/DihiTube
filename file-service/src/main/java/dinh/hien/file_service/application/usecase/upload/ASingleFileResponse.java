package dinh.hien.file_service.application.usecase.upload;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ASingleFileResponse {
    private String id;
    private String fileName;
    private Long size;
    private String storageKey;
    private String contentType;
    private Instant createdAt;
    private Boolean isPrivateFile;

    private String presignUrl;
}
