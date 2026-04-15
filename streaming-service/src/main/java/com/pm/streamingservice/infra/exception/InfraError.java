package com.pm.streamingservice.infra.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum InfraError {
    INVALID_STORAGE_KEY(1003, "Invalid storage key"),
    FAIL_GENERATE_LINK(1004, "fail to generate link"),
    FILE_SERVER_BUCKET_ERROR(1005, "File server bucket error"),
    FILE_NOT_FOUND(1006, "File not found"),
    PERMISSION_DENIED(1007, "Permission denied for this action"),;

    private final int code;
    private final String message;
}
