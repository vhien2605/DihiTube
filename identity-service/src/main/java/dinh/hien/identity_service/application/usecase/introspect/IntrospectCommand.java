package dinh.hien.identity_service.application.usecase.introspect;


import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntrospectCommand {
    private String accessToken;
}
