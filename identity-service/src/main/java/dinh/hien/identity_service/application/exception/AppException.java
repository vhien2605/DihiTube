package dinh.hien.identity_service.application.exception;

import lombok.Getter;


@Getter
public class AppException extends RuntimeException {
    private final AError aError;
    public AppException(String message, AError aError) {
        super(message);
        this.aError=aError;
    }
}
