package dinh.hien.file_service.infra.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum InfraError {
    INVALID_STORAGE_KEY(1003, "Invalid storage key"),
    FAIL_GENERATE_LINK(1004, "fail to generate link"),
    FILE_SERVER_BUCKET_ERROR(1005, "File server bucket error");
    private final int code;
    private final String message;
}
