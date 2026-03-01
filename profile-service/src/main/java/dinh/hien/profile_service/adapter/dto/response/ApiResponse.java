package dinh.hien.profile_service.adapter.dto.response;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class ApiResponse {
    private final String message;
}
