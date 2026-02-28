package com.pm.metadataservice.domain.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {
    private final DError dError;
    public DomainException(DError dError) {
        this.dError = dError;
    }
}

