package com.hien.payment_service.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AError {
    // Validation errors (20xx)
    CURRENCY_INVALID(2000, "Currency is invalid or not supported"),
    AMOUNT_INVALID(2001, "Amount must be greater than zero"),
    DESCRIPTION_REQUIRED(2002, "Description is required and cannot be empty"),
    PAYMENT_REQUEST_INVALID(2003, "Invalid payment request");

    private final int code;
    private final String message;
}

