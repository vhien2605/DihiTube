package dinh.hien.identity_service.adapter.api;



import dinh.hien.identity_service.adapter.dto.request.LoginRequestDTO;
import dinh.hien.identity_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.identity_service.adapter.dto.response.JwtResponseDTO;
import dinh.hien.identity_service.adapter.mapper.AuthMapper;
import dinh.hien.identity_service.application.usecase.login.UserLoginUseCase;
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
    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<JwtResponseDTO>> login(
            @RequestBody LoginRequestDTO dto){
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
}
