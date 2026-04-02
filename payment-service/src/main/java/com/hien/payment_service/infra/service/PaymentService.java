package com.hien.payment_service.infra.service;

import com.hien.payment_service.domain.exception.DError;
import com.hien.payment_service.domain.exception.DomainException;
import com.hien.payment_service.domain.payment.IPaymentService;
import com.hien.payment_service.domain.payment.Payment;
import com.hien.payment_service.domain.payment.StatusDomainResult;
import com.hien.payment_service.domain.payment.UserId;
import com.hien.payment_service.infra.config.VnPayConfig;
import com.hien.payment_service.infra.exception.InfraError;
import com.hien.payment_service.infra.exception.InfraException;
import com.hien.payment_service.infra.mapper.PaymentMapper;
import com.hien.payment_service.infra.model.JpaPayment;
import com.hien.payment_service.infra.repository.JpaPaymentRepository;
import com.hien.payment_service.infra.util.VNPayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService {
    private final VnPayConfig vnPayConfig;
    private final JpaPaymentRepository jpaPaymentRepository;

    @Override
    @Transactional
    public String createPayment(Payment payment, String subscriptionType, String ipAddress) {
        if (!"VND".equals(payment.getAmount().getCurrency().getCode())) {
            throw new IllegalArgumentException("VNPay only supports VND");
        }
        // create payment processing
        payment.setTransactionRef(VNPayUtil.getRandomNumber(8));
        JpaPayment jpaPayment = PaymentMapper.toJpaEntity(payment);
        jpaPaymentRepository.save(jpaPayment);

        //build vnpay url
        long vnAmount = payment.getAmount().getAmount()
                .multiply(BigDecimal.valueOf(100))
                .longValue();
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();
        vnpParamsMap.put("vnp_Amount", String.valueOf(vnAmount));
        vnpParamsMap.put("vnp_IpAddr", ipAddress);
        vnpParamsMap.put("vnp_TxnRef", payment.getTransactionRef());
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = queryUrl;
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String url = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
        return url;
    }

    @Override
    @Transactional
    public StatusDomainResult handleStatus(Map<String, String> params) {
        String receivedHash = params.get("vnp_SecureHash");
        params.remove("vnp_SecureHash");
        params.remove("vnp_SecureHashType");
        String signData = VNPayUtil.getPaymentURL(params, true);
        String calculatedHash = VNPayUtil.hmacSHA512(
                vnPayConfig.getSecretKey(),
                signData
        );
        if (!calculatedHash.equalsIgnoreCase(receivedHash)) {
            throw new InfraException(InfraError.VN_PAY_ERROR);
        }
        // status code of vnpay
        String responseCode = params.get("vnp_ResponseCode");
        String txnRef = params.get("vnp_TxnRef");

        JpaPayment jpaPayment = jpaPaymentRepository.findByTransactionRef(txnRef)
                .orElseThrow(() -> new DomainException(DError.PAYMENT_NOT_FOUND));

        boolean isSuccess = "00".equals(responseCode);
        if (isSuccess) {
            log.info("Payment SUCCESS: {}", txnRef);
            jpaPayment.setStatus("SUCCESS");
        } else {
            log.info("Payment FAILED: {}", txnRef);
            jpaPayment.setStatus("FAILED");
        }
        //validate domain
        Payment payment = PaymentMapper.toDomainEntity(jpaPayment);
        jpaPaymentRepository.save(jpaPayment);

        return new StatusDomainResult(
                isSuccess,
                jpaPayment.getSubscriptionType(),
                UserId.of(jpaPayment.getUserId())
        );
    }
}
