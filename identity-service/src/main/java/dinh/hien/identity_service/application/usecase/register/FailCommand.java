package dinh.hien.identity_service.application.usecase.register;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailCommand {
    private String userId;
}
