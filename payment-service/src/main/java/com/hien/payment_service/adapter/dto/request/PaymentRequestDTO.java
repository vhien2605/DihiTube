package com.hien.payment_service.adapter.dto.request;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {
    private BigDecimal amount;
    private String currency;
    private String description;
    private String subscriptionType;
}
