package dinh.hien.identity_service.domain.role;

import com.fasterxml.uuid.Generators;
import dinh.hien.identity_service.domain.exception.DError;
import dinh.hien.identity_service.domain.exception.DomainException;
import dinh.hien.identity_service.domain.user.UserId;
import lombok.Getter;

import java.util.UUID;


@Getter
public class RoleId {
    private final UUID value;

    private RoleId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.ID_INVALID);
        }
        this.value = value;
    }

    //uuid v6
    public static RoleId generate() {
        return new RoleId(Generators.timeBasedReorderedGenerator().generate());
    }


    public static RoleId of(UUID value) {
        return new RoleId(value);
    }

    public static RoleId of(String value) {
        return new RoleId(UUID.fromString(value));
    }
}
