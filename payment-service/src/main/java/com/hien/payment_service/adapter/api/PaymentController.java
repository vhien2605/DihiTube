package com.hien.payment_service.adapter.api;


import com.hien.payment_service.adapter.dto.request.PaymentRequestDTO;
import com.hien.payment_service.adapter.dto.response.ApiSuccessResponse;
import com.hien.payment_service.adapter.mapper.PaymentMapper;
import com.hien.payment_service.application.usecase.payment.PaymentUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PaymentController {
    private final PaymentUseCase paymentUseCase;
    private final PaymentMapper paymentMapper;

    @PostMapping("/checkout")
    public ResponseEntity<ApiSuccessResponse<String>> payment(
            @RequestBody PaymentRequestDTO dto,
            HttpServletRequest request

    ) {
        String ipAdress;
        try {
            ipAdress = request.getHeader("X-FORWARDED-FOR");
            if (ipAdress == null) {
                ipAdress = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ipAdress = "Invalid IP:" + e.getMessage();
        }
        // Map and validate DTO to Command
        var paymentCommand = paymentMapper.mapToPaymentCommand(dto, ipAdress);
        // Call usecase with validated command
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Get payment link")
                        .data(paymentUseCase.payment(paymentCommand))
                        .build();
        return ResponseEntity.ok(response);
    }
}


