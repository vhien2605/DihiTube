package com.pm.streamingservice.infra.openfeign;

import com.pm.streamingservice.adapter.dto.response.ApiSuccessResponse;
import com.pm.streamingservice.infra.config.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "profile-service", path = "/profile",
        configuration = AuthenticationRequestInterceptor.class)
public interface ProfileClient {

    @GetMapping("/is-membership")
    ApiSuccessResponse<Boolean> isMembership();
}
