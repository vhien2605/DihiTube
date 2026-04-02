package com.hien.payment_service.domain.payment;

import java.util.Map;

public interface IPaymentService {
    String createPayment(Payment payment, String subscriptionType, String ipAddress);

    StatusDomainResult handleStatus(Map<String, String> params);
}
