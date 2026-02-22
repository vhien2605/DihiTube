package dinh.hien.identity_service.adapter.mapper;

import dinh.hien.identity_service.adapter.dto.request.LoginRequestDTO;
import dinh.hien.identity_service.adapter.dto.response.JwtResponseDTO;
import dinh.hien.identity_service.application.usecase.login.LoginCommand;
import dinh.hien.identity_service.application.usecase.login.LoginResult;

public class AuthMapper {
    public static LoginCommand toLoginCommand(LoginRequestDTO dto){
        return LoginCommand.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static JwtResponseDTO toJWTResponse(LoginResult loginResult){
        return JwtResponseDTO.builder()
                .accessToken(loginResult.getAccessToken())
                .refreshToken(loginResult.getRefreshToken())
                .build();
    }
}
