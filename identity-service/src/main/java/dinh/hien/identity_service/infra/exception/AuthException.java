package dinh.hien.identity_service.infra.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}
