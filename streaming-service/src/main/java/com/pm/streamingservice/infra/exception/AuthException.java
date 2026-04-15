package com.pm.streamingservice.infra.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthException extends AuthenticationException {
    private final InfraError infraError;

      AuthException(InfraError infraError) {
        super(infraError.getMessage());
        this.infraError = infraError;
    }
}
