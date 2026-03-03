package dinh.hien.file_service.domain.file;


import dinh.hien.file_service.domain.file.exception.DError;
import dinh.hien.file_service.domain.file.exception.DomainException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class FileMetaData {
    private final FileId id;
    private final String fileName;
    private final FileSize size;
    private final String contentType;
    private final String storageKey;
    private final FileType type;
    private final Instant createdAt;
    private final Boolean isPrivateFile;
    
    public static FileMetaData of(
            FileId id,
            String fileName,
            FileSize size,
            String contentType,
            String storageKey
    ) {
        if (contentType == null || contentType.isBlank()) {
            throw new DomainException(DError.FILE_CONTENT_TYPE_INVALID);
        }
        FileType type;
        boolean isPrivate;

        if (contentType.startsWith("image/")) {
            type = FileType.IMAGE;
            isPrivate = false;
        } else if (contentType.startsWith("video/")) {
            type = FileType.VIDEO;
            isPrivate = true;
        } else {
            type = FileType.OTHER;
            isPrivate = true;
        }
        return new FileMetaData(
                id,
                fileName,
                size,
                contentType,
                storageKey,
                type,
                Instant.now(),
                isPrivate
        );
    }
}
