package com.hien.notification_service.adapter.exception;

import com.hien.notification_service.adapter.dto.response.ApiErrorResponse;
import com.hien.notification_service.domain.exception.DomainException;
import com.hien.notification_service.infra.exception.InfraException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Map<Integer, HttpStatus> httpStatusMap = Map.of(
            1001, HttpStatus.BAD_REQUEST,  // NOTIFICATION_ID_INVALID
            1002, HttpStatus.BAD_REQUEST,  // USER_ID_INVALID
            1003, HttpStatus.BAD_REQUEST,  // NOTIFICATION_TYPE_INVALID
            1004, HttpStatus.BAD_REQUEST,  // NOTIFICATION_STATUS_INVALID
            1005, HttpStatus.BAD_REQUEST,  // NOTIFICATION_TITLE_INVALID
            1006, HttpStatus.BAD_REQUEST,  // NOTIFICATION_CONTENT_INVALID
            1007, HttpStatus.BAD_REQUEST
    );


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

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e, WebRequest request) {
        log.info("---------------------------Access denied exception handler start---------------------------");
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiErrorResponse.builder()
                                .error(HttpStatus.FORBIDDEN.name())
                                .message(e.getMessage())
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
