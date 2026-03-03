package dinh.hien.file_service.domain.file.exception;


import lombok.Getter;


@Getter
public class DomainException extends RuntimeException {
    private final DError dError;

    public DomainException(DError dError) {
        this.dError = dError;
    }
}
