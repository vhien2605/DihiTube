package com.hien.payment_service.application.usecase.success;

import com.hien.payment_service.domain.payment.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SuccessUseCase {
    private final IPaymentService paymentService;

    public void paymentSuccess(Map<String, String> params) {
        paymentService.handleSuccess(params);
    }
}
