package com.hien.payment_service.domain.payment;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatusDomainResult {
    private Boolean isSuccess;
    private String subscriptionType;
    private UserId userId;
}
