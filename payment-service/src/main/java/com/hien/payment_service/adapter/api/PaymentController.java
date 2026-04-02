package com.hien.payment_service.adapter.api;


import com.hien.payment_service.adapter.dto.request.PaymentRequestDTO;
import com.hien.payment_service.adapter.dto.response.ApiSuccessResponse;
import com.hien.payment_service.adapter.mapper.PaymentMapper;
import com.hien.payment_service.application.usecase.payment.PaymentUseCase;
import com.hien.payment_service.application.usecase.success.SuccessUseCase;
import com.hien.payment_service.infra.util.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class PaymentController {
    private final PaymentUseCase paymentUseCase;
    private final PaymentMapper paymentMapper;
    private final SuccessUseCase successUseCase;

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
        var paymentCommand = paymentMapper.mapToPaymentCommand(dto, ipAdress,
                SecurityUtil.getSubFromToken()
        );
        // Call usecase with validated command
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("Get payment link")
                        .data(paymentUseCase.payment(paymentCommand))
                        .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/vnpay/return")
    public ResponseEntity<ApiSuccessResponse<Void>> ipnReturn(
            @RequestParam Map<String, String> params
    ) {
        log.info("All params: {}", params);
        successUseCase.paymentSuccess(params);
        ApiSuccessResponse<Void> response =
                ApiSuccessResponse.<Void>builder()
                        .message("vnpay ipn")
                        .build();
        return ResponseEntity.ok(response);
    }
}


