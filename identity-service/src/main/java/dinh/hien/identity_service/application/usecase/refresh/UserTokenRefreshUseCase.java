package dinh.hien.identity_service.application.usecase.refresh;

import dinh.hien.identity_service.application.service.token.ITokenService;
import dinh.hien.identity_service.application.service.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTokenRefreshUseCase {
    private final ITokenService tokenService;
    public RefreshResult refresh(RefreshCommand command){
        String refreshToken=command.getRefreshToken();
        var payload=tokenService.verify(refreshToken, TokenType.REFRESH);
        return RefreshResult.builder()
                .accessToken(tokenService.generate(payload, TokenType.ACCESS))
                .build();
    }
}
