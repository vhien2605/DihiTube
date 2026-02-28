package dinh.hien.identity_service.adapter.api;


import dinh.hien.identity_service.adapter.dto.request.LoginRequestDTO;
import dinh.hien.identity_service.adapter.dto.request.RefreshRequestDTO;
import dinh.hien.identity_service.adapter.dto.request.RegisterRequestDTO;
import dinh.hien.identity_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.identity_service.adapter.dto.response.auth.JwtResponseDTO;
import dinh.hien.identity_service.adapter.dto.response.auth.RefreshResponse;
import dinh.hien.identity_service.adapter.mapper.AuthMapper;
import dinh.hien.identity_service.application.usecase.login.UserLoginUseCase;
import dinh.hien.identity_service.application.usecase.refresh.RefreshCommand;
import dinh.hien.identity_service.application.usecase.refresh.UserTokenRefreshUseCase;
import dinh.hien.identity_service.application.usecase.register.RegisterCommand;
import dinh.hien.identity_service.application.usecase.register.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserLoginUseCase userLoginUseCase;
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserTokenRefreshUseCase userTokenRefreshUseCase;

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<JwtResponseDTO>> login(
            @RequestBody LoginRequestDTO dto) {
        JwtResponseDTO dtoRes = AuthMapper.toJWTResponse(
                userLoginUseCase.loginUser(AuthMapper.toLoginCommand(dto))
        );
        ApiSuccessResponse<JwtResponseDTO> response =
                ApiSuccessResponse.<JwtResponseDTO>builder()
                        .message("Login ok")
                        .data(dtoRes)
                        .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiSuccessResponse<String>> register(
            @RequestBody RegisterRequestDTO dto) {
        RegisterCommand command = AuthMapper.toRegisterCommand(dto);
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("profile is processing")
                        .data(userRegisterUseCase.registerUser(command))
                        .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/refresh")
    public ResponseEntity<ApiSuccessResponse<RefreshResponse>> refresh(
            @RequestBody RefreshRequestDTO dto) {
        RefreshCommand command = RefreshCommand.builder().refreshToken(dto.getRefreshToken()).build();
        var usecaseResult = userTokenRefreshUseCase.refresh(command);
        ApiSuccessResponse<RefreshResponse> response =
                ApiSuccessResponse.<RefreshResponse>builder()
                        .message("refresh token")
                        .data(RefreshResponse.builder()
                                .accessToken(usecaseResult.getAccessToken())
                                .build())
                        .build();
        return ResponseEntity.ok(response);
    }
}
