package dinh.hien.file_service.domain.file.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    FILE_ID_INVALID(1000, "File ID is invalid"),
    FILE_SIZE_INVALID(1001, "File size is invalid"),
    FILE_CONTENT_TYPE_INVALID(1002, "File content type is invalid"),
    ;
    private final int code;
    private final String message;
}
