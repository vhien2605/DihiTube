package com.hien.payment_service.infra.config.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.error("Access failed: {}", ex.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        ObjectNode body = objectMapper.createObjectNode()
                .put("error", HttpStatus.FORBIDDEN.name())
                .put("message", ex.getMessage())
                .put("path", request.getRequestURI());
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
