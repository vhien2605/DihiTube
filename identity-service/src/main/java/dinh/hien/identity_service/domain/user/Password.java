package dinh.hien.identity_service.domain.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Password {
    private final String hashValue;

    private Password(String hashValue) {
        if (hashValue== null || hashValue.isBlank()) {
            throw new IllegalArgumentException("Password value must not be null or blank");
        }
        this.hashValue=hashValue;
    }

    public boolean verify(String rawPassword, PasswordHasher hasher) {
        return hasher.matches(rawPassword, this.hashValue);
    }

    public static Password of(String hashValue) {
        return new Password(hashValue);
    }
}
