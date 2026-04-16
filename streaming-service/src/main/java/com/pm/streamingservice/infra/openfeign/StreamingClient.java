package com.pm.streamingservice.infra.openfeign;

import com.pm.streamingservice.adapter.dto.response.ApiSuccessResponse;
import com.pm.streamingservice.infra.config.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "file-service", path = "/file",
        configuration = AuthenticationRequestInterceptor.class)
public interface StreamingClient {

    @GetMapping("/stream")
    ApiSuccessResponse<String> metadata(@RequestParam("id") UUID id);
}
