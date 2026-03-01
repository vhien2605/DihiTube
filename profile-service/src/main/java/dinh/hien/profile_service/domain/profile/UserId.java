package dinh.hien.profile_service.domain.profile;

import com.fasterxml.uuid.Generators;
import dinh.hien.profile_service.domain.exception.DError;
import dinh.hien.profile_service.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Setter
@Getter
public class UserId {
    private final UUID value;

    private UserId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.USER_ID_INVALID);
        }
        this.value = value;
    }

    public static UserId generate() {
        //uuidv6
        return new UserId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static UserId of(UUID value) {
        return new UserId(value);
    }

    public static UserId of(String value) {
        return new UserId(UUID.fromString(value));
    }
}
