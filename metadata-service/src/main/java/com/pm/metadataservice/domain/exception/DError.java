package com.pm.metadataservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    ;
    private final int code;
    private final String message;
}
