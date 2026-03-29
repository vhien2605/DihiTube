package com.hien.payment_service.infra.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum InfraError {
    JWT_SIGN_ERROR(1002, "Something wrong with sign jwt when gen token"),
    JWT_INVALID_SIGNATURE(1003, "Your signature was invalid"),
    JWT_INVALID(1004, "token invalid"),
    JWT_EXPIRED(1005, "Token expired"),
    TOKEN_DISABLE(1011, "Token is disabled"),
    ;
    private final int code;
    private final String message;
}
