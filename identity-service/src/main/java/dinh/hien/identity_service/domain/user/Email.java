package dinh.hien.identity_service.domain.user;

import lombok.Getter;

import java.util.regex.Pattern;


@Getter
public class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public static Email of(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Email must not be empty");
        }
        String normalized = raw.trim().toLowerCase();
        if (!isValid(normalized)) {
            throw new IllegalArgumentException("Invalid email format: " + raw);
        }
        return new Email(normalized);
    }

    private static boolean isValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
}