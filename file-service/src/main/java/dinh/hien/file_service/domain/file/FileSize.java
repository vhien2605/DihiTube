package dinh.hien.file_service.domain.file;

import dinh.hien.file_service.domain.file.exception.DError;
import dinh.hien.file_service.domain.file.exception.DomainException;
import lombok.Getter;


@Getter
public class FileSize {
    private final long value;

    private FileSize(long value) {
        this.value = value;
    }

    public static FileSize of(long value) {
        if (value <= 0) {
            throw new DomainException(DError.FILE_SIZE_INVALID);
        }
        return new FileSize(value);
    }
}
