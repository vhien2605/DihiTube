package dinh.hien.profile_service.infra.external;

import dinh.hien.profile_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.profile_service.adapter.dto.request.PaymentLinkRequest;
import dinh.hien.profile_service.infra.config.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "payment-service", path = "/payment",
        configuration = AuthenticationRequestInterceptor.class
)
public interface PaymentClient {
    @PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiSuccessResponse<String>> getPaymentLink(@RequestBody PaymentLinkRequest request);
}
