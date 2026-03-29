package com.hien.payment_service.application.exception;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {
    private final AError aError;

    public ApplicationException(AError aError) {
        super(aError.getMessage());
        this.aError = aError;
    }
}

