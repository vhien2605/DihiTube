package com.hien.payment_service.infra.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {

    private SecurityUtil() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    public static String getSubFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
