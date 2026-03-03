package dinh.hien.profile_service.adapter.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ApiResponse {
    private String message;
}
