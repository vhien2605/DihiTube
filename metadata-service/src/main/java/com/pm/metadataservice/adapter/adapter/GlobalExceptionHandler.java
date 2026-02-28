package com.pm.metadataservice.adapter.adapter;

import com.pm.metadataservice.adapter.dto.response.ApiErrorResponse;
import com.pm.metadataservice.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Map<Integer, HttpStatus> httpStatusMap = Map.of(
            1000, HttpStatus.BAD_REQUEST,
            1001,HttpStatus.UNAUTHORIZED,
            1002,HttpStatus.UNAUTHORIZED,
            1003,HttpStatus.UNAUTHORIZED,
            1004,HttpStatus.UNAUTHORIZED,
            1005,HttpStatus.UNAUTHORIZED,
            1006,HttpStatus.BAD_REQUEST,
            1007,HttpStatus.BAD_REQUEST,
            1008,HttpStatus.BAD_REQUEST,
            1010,HttpStatus.BAD_REQUEST
    );

    @ExceptionHandler({DomainException.class})
    public ResponseEntity handleAppException(DomainException e, WebRequest request) {
        log.info("---------------------------Application exception handler start---------------------------");
        HttpStatus status=httpStatusMap.get(e.getDError().getCode());
        return ResponseEntity.status(status)
                .body(
                        ApiErrorResponse.builder()
                                .error(e.getDError().name())
                                .message(e.getDError().getMessage())
                                .path(request.getDescription(false))
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .toList();

        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "errors", errors
                )
        );
    }
}