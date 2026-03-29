package com.hien.payment_service.infra.exception;


import lombok.Getter;

@Getter
public class InfraException extends RuntimeException {
    private final InfraError error;

    public InfraException(String message, InfraError error) {
        super(message);
        this.error = error;
    }
}
