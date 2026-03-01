package dinh.hien.profile_service.domain.profile;

import com.fasterxml.uuid.Generators;
import dinh.hien.profile_service.domain.exception.DError;
import dinh.hien.profile_service.domain.exception.DomainException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProfileId {
    private final UUID value;

    private ProfileId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.PROFILE_ID_INVALID);
        }
        this.value = value;
    }

    public static ProfileId generate() {
        //uuidv6
        return new ProfileId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static ProfileId of(UUID value) {
        return new ProfileId(value);
    }

    public static ProfileId of(String value) {
        return new ProfileId(UUID.fromString(value));
    }
}
