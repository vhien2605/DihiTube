package com.hien.payment_service.application.usecase.payment;


import com.hien.payment_service.domain.payment.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentUseCase {
    private final IPaymentService paymentService;

    public String payment(PaymentCommand paymentCommand) {
        Payment payment = Payment.create(Money.of(paymentCommand.getAmount(), Currency.valueOf(paymentCommand.getCurrency()))
                , paymentCommand.getDescription(),
                UserId.of(paymentCommand.getUserId())
        );
        return paymentService.createPayment(
                payment,
                paymentCommand.getIpAddress()
        );
    }
}
