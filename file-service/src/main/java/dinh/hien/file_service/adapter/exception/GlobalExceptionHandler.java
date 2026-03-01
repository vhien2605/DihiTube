package dinh.hien.file_service.adapter.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class GlobalExceptionHandler {
    private static final Map<Integer, HttpStatus> httpStatusMap = Map.of(
            1000, HttpStatus.BAD_REQUEST,
            1001, HttpStatus.BAD_REQUEST,
            1002, HttpStatus.BAD_REQUEST
    );
}
