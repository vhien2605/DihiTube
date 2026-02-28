package dinh.hien.identity_service.adapter.dto.response.auth;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectResponseDTO {
    private boolean isValid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
