package com.hien.payment_service.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Currency {
    VND("VND", "Vietnamese Dong"),
    USD("USD", "US Dollar"),
    CNY("CNY", "Chinese Yuan");

    private final String code;
    private final String displayName;
}

