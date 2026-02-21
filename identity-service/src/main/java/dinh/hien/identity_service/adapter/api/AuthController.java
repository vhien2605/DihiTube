package dinh.hien.identity_service.adapter.api;



import dinh.hien.identity_service.adapter.dto.request.LoginRequestDTO;
import dinh.hien.identity_service.adapter.mapper.AuthMapper;
import dinh.hien.identity_service.application.usecase.login.UserLoginUseCase;
import lombok.RequiredArgsConstructor;
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
    public String login(
            @RequestBody LoginRequestDTO dto){
        userLoginUseCase.userLogin(AuthMapper.toLoginCommand(dto));
        return "ok";
    }
}
