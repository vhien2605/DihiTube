package com.hien.payment_service.application.usecase.payment;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCommand {
    private BigDecimal amount;
    private String currency;
    private String description;
    private String ipAddress;
}
