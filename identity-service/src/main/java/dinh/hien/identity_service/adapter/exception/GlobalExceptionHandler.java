package dinh.hien.identity_service.adapter.exception;



import dinh.hien.identity_service.adapter.dto.response.ApiErrorResponse;
import dinh.hien.identity_service.adapter.dto.response.ApiResponse;
import dinh.hien.identity_service.domain.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Map<Integer, HttpStatus> httpStatusMap = Map.of(
            1000, HttpStatus.BAD_REQUEST
    );


//    @ExceptionHandler({DomainException.class})
//    public ApiResponse handleAppException(DomainException e, WebRequest request) {
//        log.info("---------------------------Application exception handler start---------------------------");
//        String error = e.getMessage();
//        return ApiErrorResponse.builder()
//                .status(e.getErrorCode().getCode())
//                .message(e.getErrorCode().getMessage())
//                .error(e.getErrorCode().name())
//                .path(request.getDescription(false))
//                .build();
//    }
}
