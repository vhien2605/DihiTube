package dinh.hien.profile_service.domain.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;


@Getter
public class PhoneNumber {
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?[0-9]{8,15}$");

    private final String value;

    private PhoneNumber(String value) {
        this.value = value;
    }

    public static PhoneNumber of(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }

        String normalized = normalize(raw);

        if (!PHONE_PATTERN.matcher(normalized).matches()) {
            throw new IllegalArgumentException("Invalid phone number: " + raw);
        }

        return new PhoneNumber(normalized);
    }

    // chuan hoa so co ki tu dac biet
    private static String normalize(String input) {
        return input.replaceAll("[\\s\\-()]", "");
    }
}
