package com.hien.payment_service.adapter.mapper;

import com.hien.payment_service.adapter.dto.request.PaymentRequestDTO;
import com.hien.payment_service.application.exception.AError;
import com.hien.payment_service.application.exception.ApplicationException;
import com.hien.payment_service.application.usecase.payment.PaymentCommand;
import com.hien.payment_service.domain.payment.Currency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PaymentMapper {
    public PaymentCommand mapToPaymentCommand(PaymentRequestDTO dto, String ipAdress, String userId) {
        // Validate amount
        if (dto.getAmount() == null || dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ApplicationException(AError.AMOUNT_INVALID);
        }
        // Validate currency
        String currencyCode = validateAndGetCurrencyCode(dto.getCurrency());

        // Validate description
        if (dto.getDescription() == null || dto.getDescription().trim().isEmpty()) {
            throw new ApplicationException(AError.DESCRIPTION_REQUIRED);
        }
        return PaymentCommand.builder()
                .amount(dto.getAmount())
                .currency(currencyCode)
                .description(dto.getDescription().trim())
                .ipAddress(ipAdress)
                .userId(userId)
                .subscriptionType(dto.getSubscriptionType())
                .build();
    }

    private String validateAndGetCurrencyCode(String currencyCode) {
        if (currencyCode == null || currencyCode.trim().isEmpty()) {
            throw new ApplicationException(AError.CURRENCY_INVALID);
        }
        String normalizedCode = currencyCode.trim().toUpperCase();
        try {
            Currency.valueOf(normalizedCode);
            return normalizedCode;
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(AError.CURRENCY_INVALID);
        }
    }
}


