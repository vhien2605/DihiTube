package dinh.hien.file_service.infra.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum InfraError {
    ;
    private final int code;
    private final String message;
}
