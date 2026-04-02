package com.hien.payment_service.adapter.exception;


import com.hien.payment_service.adapter.dto.response.ApiErrorResponse;
import com.hien.payment_service.application.exception.ApplicationException;
import com.hien.payment_service.domain.exception.DomainException;
import com.hien.payment_service.infra.exception.InfraException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Map<Integer, HttpStatus> httpStatusMap = Map.ofEntries(
            // Application errors (20xx)
            Map.entry(2000, HttpStatus.BAD_REQUEST),     // CURRENCY_INVALID (application)
            Map.entry(2001, HttpStatus.BAD_REQUEST),     // AMOUNT_INVALID (application)
            Map.entry(2002, HttpStatus.BAD_REQUEST),     // DESCRIPTION_REQUIRED (application)
            Map.entry(2003, HttpStatus.BAD_REQUEST),     // PAYMENT_REQUEST_INVALID (application)
            // Domain errors (10xx)
            Map.entry(1000, HttpStatus.BAD_REQUEST),     // MONEY_INVALID
            Map.entry(1001, HttpStatus.NOT_FOUND),       // PAYMENT_ID_INVALID
            Map.entry(1002, HttpStatus.CONFLICT),        // PAYMENT_STATUS_INVALID (domain)
            Map.entry(1003, HttpStatus.BAD_REQUEST),     // PAYMENT_DESCRIPTION_INVALID (domain)
            Map.entry(1004, HttpStatus.NOT_FOUND),       // PAYMENT_NOT_FOUND (domain)
            Map.entry(1005, HttpStatus.BAD_REQUEST),     // CURRENCY_INVALID (domain)
            Map.entry(1006, HttpStatus.BAD_REQUEST),     // AMOUNT_INVALID (domain)
            Map.entry(1007, HttpStatus.BAD_REQUEST),     // DESCRIPTION_REQUIRED (domain)
            Map.entry(1011, HttpStatus.FORBIDDEN)        // TOKEN_DISABLE (infra)
    );

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity handleApplicationException(ApplicationException e, WebRequest request) {
        log.info("---------------------------Application exception handler start---------------------------");
        HttpStatus status = httpStatusMap.get(e.getAError().getCode());
        return ResponseEntity.status(status)
                .body(
                        ApiErrorResponse.builder()
                                .error(e.getAError().name())
                                .message(e.getAError().getMessage())
                                .path(request.getDescription(false))
                                .build()
                );
    }

    @ExceptionHandler({DomainException.class})
    public ResponseEntity handleAppException(DomainException e, WebRequest request) {
        log.info("---------------------------Domain exception handler start---------------------------");
        HttpStatus status = httpStatusMap.get(e.getDError().getCode());
        return ResponseEntity.status(status)
                .body(
                        ApiErrorResponse.builder()
                                .error(e.getDError().name())
                                .message(e.getDError().getMessage())
                                .path(request.getDescription(false))
                                .build()
                );
    }


    @ExceptionHandler({InfraException.class})
    public ResponseEntity handleAppException(InfraException e, WebRequest request) {
        log.info("---------------------------Infra exception handler start---------------------------");
        HttpStatus status = httpStatusMap.get(e.getError().getCode());
        return ResponseEntity.status(status)
                .body(
                        ApiErrorResponse.builder()
                                .error(e.getError().name())
                                .message(e.getError().getMessage())
                                .path(request.getDescription(false))
                                .build()
                );
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity handleGeneralException(Exception e, WebRequest request) {
        log.info("---------------------------general exception handler start---------------------------");
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiErrorResponse.builder()
                                .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                                .message(e.getMessage())
                                .path(request.getDescription(false))
                                .build()
                );
    }
}
