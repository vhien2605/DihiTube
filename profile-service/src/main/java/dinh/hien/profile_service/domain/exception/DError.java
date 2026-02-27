package dinh.hien.identity_service.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    USER_NOT_EXISTED(1000, "Your credentials were invalid, please check again"),
    USER_EXISTED(1006, "Username registered, please try other usernames"),
    PASSWORD_INVALID(1001,"Your credentials were invalid, please check again"),
    ROLE_NOT_EXISTED(1007,"role is not existed"),
    ID_INVALID(1008,"Id is invalid"),
    EMAIL_INVALID(1010,"Email invalid")
    ;
    private final int code;
    private final String message;
}
