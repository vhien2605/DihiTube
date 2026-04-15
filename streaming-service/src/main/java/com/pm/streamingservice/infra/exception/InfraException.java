package com.pm.streamingservice.infra.exception;


import lombok.Getter;

@Getter
public class InfraException extends RuntimeException {
    private final InfraError error;

    public InfraException(InfraError error) {
        super(error.getMessage());
        this.error = error;
    }
}
