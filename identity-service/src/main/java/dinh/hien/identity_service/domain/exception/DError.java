package dinh.hien.identity_service.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    USER_NOT_EXISTED(1000, "Your credentials were invalid, please check again"),
    PASSWORD_INVALID(1001,"Your credentials were invalid, please check again")
    ;
    private final int code;
    private final String message;
}
