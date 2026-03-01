package dinh.hien.identity_service.application.usecase.introspect;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectResult {
    private boolean isValid;
    private String message;
}
