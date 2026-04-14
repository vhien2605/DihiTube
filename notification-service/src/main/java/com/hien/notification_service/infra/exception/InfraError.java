package com.hien.notification_service.infra.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum InfraError {
    MAIL_SERVER_ERROR(1008, "Mail server is crashed");
    private final int code;
    private final String message;
}
