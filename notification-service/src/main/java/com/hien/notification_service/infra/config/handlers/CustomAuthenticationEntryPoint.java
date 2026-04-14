package com.hien.notification_service.infra.config.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hien.notification_service.infra.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.error("Authentication failed: {}", authException.getMessage());
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        ObjectNode body = objectMapper.createObjectNode();
        if (authException instanceof AuthException exception) {
            body.put("error", exception.getInfraError().name());
            body.put("message", exception.getInfraError().getMessage());
        } else {
            body.put("error", HttpStatus.UNAUTHORIZED.name());
            body.put("message", authException.getMessage());
        }
        body.put("path", request.getRequestURI());
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}

