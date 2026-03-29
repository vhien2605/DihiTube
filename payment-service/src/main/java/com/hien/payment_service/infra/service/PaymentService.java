package com.hien.payment_service.infra.service;

import com.hien.payment_service.domain.payment.IPaymentService;
import com.hien.payment_service.domain.payment.Money;
import com.hien.payment_service.infra.config.VnPayConfig;
import com.hien.payment_service.infra.util.VNPayUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final VnPayConfig vnPayConfig;

    @Override
    public String createPayment(Money amount, String description, String ipAddress) {
        if (!"VND".equals(amount.getCurrency().getCode())) {
            throw new IllegalArgumentException("VNPay only supports VND");
        }
        long vnAmount = amount.getAmount()
                .multiply(BigDecimal.valueOf(100))
                .longValue();
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(vnAmount));
        vnpParamsMap.put("vnp_IpAddr", ipAddress);

        // 1. Build query url (ĐÃ CÓ URL ENCODE)
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);

        // 2. Build hashData (SỬA LỖI Ở ĐÂY: Sử dụng luôn queryUrl vì nó đã được URL Encode chuẩn xác)
        String hashData = queryUrl;

        // 3. Tạo chữ ký bảo mật
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String url = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        
        return url;
    }

    @Override
    public void handleSuccess(Map<String, String> params) {
        // 1. Lấy hash
        String receivedHash = params.get("vnp_SecureHash");

        // 2. Remove các field không dùng để ký
        params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");

        // 3. Build sign data (KHÔNG encode key)
        String signData = VNPayUtil.getPaymentURL(params, false);

        // 4. Tính lại hash
        String calculatedHash = VNPayUtil.hmacSHA512(
                vnPayConfig.getSecretKey(),
                signData
        );

        // 5. Validate chữ ký
        if (!calculatedHash.equalsIgnoreCase(receivedHash)) {
            throw new RuntimeException("Invalid signature");
        }

        // 6. Lấy data
        String responseCode = params.get("vnp_ResponseCode");
        String txnRef = params.get("vnp_TxnRef");
        String amount = params.get("vnp_Amount");

        // 7. Business logic
        if ("00".equals(responseCode)) {
            System.out.println("Payment SUCCESS: " + txnRef);
            // TODO:
            // - check order tồn tại
            // - check chưa xử lý (idempotent)
            // - verify amount
            // - update SUCCESS
        } else {
            System.out.println("Payment FAILED: " + txnRef);

            // TODO:
            // update FAILED
        }
    }
}
