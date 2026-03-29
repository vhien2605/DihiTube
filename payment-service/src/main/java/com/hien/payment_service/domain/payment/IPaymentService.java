package com.hien.payment_service.domain.payment;

import java.util.Map;

public interface IPaymentService {
    String createPayment(Money amount, String description, String ipAddress);

    void handleSuccess(Map<String, String> params);
}
