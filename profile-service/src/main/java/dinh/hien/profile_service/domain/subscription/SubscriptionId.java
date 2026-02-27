package dinh.hien.profile_service.domain.subscription;

import com.fasterxml.uuid.Generators;
import lombok.Getter;

import java.util.UUID;


@Getter
public class SubscriptionId {
    private final UUID value;

    private SubscriptionId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("SubscriptionId must not be null");
        }
        this.value = value;
    }

    public static SubscriptionId generate() {
        //uuidv6
        return new SubscriptionId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static SubscriptionId of(UUID value) {
        return new SubscriptionId(value);
    }

    public static SubscriptionId of(String value) {
        return new SubscriptionId(UUID.fromString(value));
    }
}
