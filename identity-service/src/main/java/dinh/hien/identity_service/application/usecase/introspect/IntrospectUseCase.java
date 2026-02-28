package dinh.hien.identity_service.application.usecase.introspect;


import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenType;
import dinh.hien.identity_service.infra.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IntrospectUseCase {
    private final ITokenService tokenService;

    public IntrospectResult introspect(IntrospectCommand command) {
        try {
            tokenService.verify(command.getAccessToken(), TokenType.ACCESS);
            return IntrospectResult.builder().isValid(true).build();
        } catch (AuthException exception) {
            return IntrospectResult.builder().isValid(false)
                    .message(exception.getMessage())
                    .build();
        }
    }
}
