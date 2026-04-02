package com.hien.payment_service.domain.payment;

import java.util.Map;

public interface IPaymentService {
    String createPayment(Payment payment, String ipAddress);

    void handleSuccess(Map<String, String> params);
}
