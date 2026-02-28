package dinh.hien.gateway.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectResponseDTO {
    private Boolean isValid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;
}
