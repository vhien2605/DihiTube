package dinh.hien.identity_service.domain.user;

import dinh.hien.identity_service.domain.exception.DError;
import dinh.hien.identity_service.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Password {
    private final String hashValue;

    private Password(String hashValue) {
        if (hashValue== null || hashValue.isBlank()) {
            throw new DomainException(DError.PASSWORD_INVALID);
        }
        this.hashValue=hashValue;
    }

    public boolean verify(String rawPassword, PasswordHasher hasher) {
        return hasher.matches(rawPassword, this.hashValue);
    }

    public static Password of(String hashValue) {
        return new Password(hashValue);
    }

    public static Password fromRawValue(String rawValue,PasswordHasher hasher){
        return new Password(hasher.hash(rawValue));
    }
}
