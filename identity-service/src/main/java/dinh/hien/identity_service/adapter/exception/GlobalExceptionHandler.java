package dinh.hien.identity_service.adapter.exception;


import dinh.hien.identity_service.adapter.dto.response.ApiErrorResponse;
import dinh.hien.identity_service.domain.exception.DomainException;
import dinh.hien.identity_service.infra.exception.InfraException;
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
            Map.entry(1000, HttpStatus.BAD_REQUEST),
            Map.entry(1001, HttpStatus.UNAUTHORIZED),
            Map.entry(1002, HttpStatus.UNAUTHORIZED),
            Map.entry(1003, HttpStatus.UNAUTHORIZED),
            Map.entry(1004, HttpStatus.UNAUTHORIZED),
            Map.entry(1005, HttpStatus.UNAUTHORIZED),
            Map.entry(1006, HttpStatus.BAD_REQUEST),
            Map.entry(1007, HttpStatus.BAD_REQUEST),
            Map.entry(1008, HttpStatus.BAD_REQUEST),
            Map.entry(1010, HttpStatus.BAD_REQUEST),
            Map.entry(1011, HttpStatus.UNAUTHORIZED)
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
