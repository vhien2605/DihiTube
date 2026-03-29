package com.hien.payment_service.application.usecase.payment;


import com.hien.payment_service.domain.payment.Currency;
import com.hien.payment_service.domain.payment.IPaymentService;
import com.hien.payment_service.domain.payment.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentUseCase {
    private final IPaymentService paymentService;

    public String payment(PaymentCommand paymentCommand) {
        return paymentService.createPayment(
                Money.of(paymentCommand.getAmount(),
                        Currency.valueOf(paymentCommand.getCurrency())),
                paymentCommand.getDescription(),
                paymentCommand.getIpAddress()
        );
    }
}
