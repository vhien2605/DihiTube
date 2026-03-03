package dinh.hien.file_service.domain.file;

import com.fasterxml.uuid.Generators;
import dinh.hien.file_service.domain.file.exception.DError;
import dinh.hien.file_service.domain.file.exception.DomainException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class FileId {
    private final UUID value;

    private FileId(UUID value) {
        if (value == null) {
            throw new DomainException(DError.FILE_ID_INVALID);
        }
        this.value = value;
    }

    public static FileId generate() {
        //uuidv6
        return new FileId(Generators.timeBasedReorderedGenerator().generate());
    }

    public static FileId of(UUID value) {
        return new FileId(value);
    }

    public static FileId of(String value) {
        return new FileId(UUID.fromString(value));
    }
}
