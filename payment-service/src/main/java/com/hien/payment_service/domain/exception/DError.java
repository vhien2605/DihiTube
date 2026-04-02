package com.hien.payment_service.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    MONEY_INVALID(1000, "Money amount is invalid"),
    PAYMENT_ID_INVALID(1001, "payment id is invalid"),
    PAYMENT_STATUS_INVALID(1002, "Payment status transition is invalid"),
    PAYMENT_DESCRIPTION_INVALID(1003, "Payment description is invalid"),
    PAYMENT_NOT_FOUND(1004, "Payment not found"),
    CURRENCY_INVALID(1005, "Currency is invalid or not supported"),
    AMOUNT_INVALID(1006, "Amount must be greater than zero"),
    DESCRIPTION_REQUIRED(1007, "Description is required and cannot be empty"),
    ID_INVALID(1008, "ID is invalid");

    private final int code;
    private final String message;
}
