package dinh.hien.identity_service.infra.exception;

import lombok.Getter;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthException extends AuthenticationException {
    private  InfraError infraError;
    public AuthException(InfraError infraError) {
        super(infraError.getMessage());
        this.infraError=infraError;
    }
}
