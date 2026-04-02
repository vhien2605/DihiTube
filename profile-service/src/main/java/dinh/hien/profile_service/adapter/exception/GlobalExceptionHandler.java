package dinh.hien.profile_service.adapter.exception;


import dinh.hien.profile_service.adapter.dto.response.ApiErrorResponse;
import dinh.hien.profile_service.domain.exception.DomainException;
import dinh.hien.profile_service.infra.exception.InfraException;
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
    private static final Map<Integer, HttpStatus> httpStatusMap = Map.of(
            1000, HttpStatus.BAD_REQUEST,
            1001, HttpStatus.BAD_REQUEST,
            1002, HttpStatus.BAD_REQUEST,
            1003, HttpStatus.BAD_REQUEST,
            1004, HttpStatus.BAD_REQUEST,
            1005, HttpStatus.NOT_FOUND,
            1006, HttpStatus.NOT_FOUND
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
}
