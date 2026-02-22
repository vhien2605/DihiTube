package dinh.hien.identity_service.adapter.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
@SuperBuilder
public abstract class ApiResponse {
    private final String message;
}
