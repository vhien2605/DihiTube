package dinh.hien.identity_service.application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AError {
    ;
    private final int code;
    private final String message;
}
