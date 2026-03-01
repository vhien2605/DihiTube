package dinh.hien.identity_service.application.usecase.refresh;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshCommand {
    private String refreshToken;
}
