package dinh.hien.identity_service.adapter.mapper;

import dinh.hien.identity_service.adapter.dto.request.ChangePasswordRequestDTO;
import dinh.hien.identity_service.adapter.dto.request.IntrospectRequestDTO;
import dinh.hien.identity_service.adapter.dto.request.LoginRequestDTO;
import dinh.hien.identity_service.adapter.dto.request.RegisterRequestDTO;
import dinh.hien.identity_service.adapter.dto.response.auth.IntrospectResponseDTO;
import dinh.hien.identity_service.adapter.dto.response.auth.JwtResponseDTO;
import dinh.hien.identity_service.adapter.messaging.event.UserProfileCreatedFailEvent;
import dinh.hien.identity_service.application.usecase.introspect.IntrospectCommand;
import dinh.hien.identity_service.application.usecase.introspect.IntrospectResult;
import dinh.hien.identity_service.application.usecase.login.LoginCommand;
import dinh.hien.identity_service.application.usecase.login.LoginResult;
import dinh.hien.identity_service.application.usecase.password.ChangePasswordCommand;
import dinh.hien.identity_service.application.usecase.register.FailCommand;
import dinh.hien.identity_service.application.usecase.register.RegisterCommand;

public class AuthMapper {
    public static LoginCommand toLoginCommand(LoginRequestDTO dto) {
        return LoginCommand.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static JwtResponseDTO toJWTResponse(LoginResult loginResult) {
        return JwtResponseDTO.builder()
                .accessToken(loginResult.getAccessToken())
                .refreshToken(loginResult.getRefreshToken())
                .build();
    }

    public static RegisterCommand toRegisterCommand(RegisterRequestDTO dto) {
        return RegisterCommand.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .displayName(dto.getDisplayName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static FailCommand toFailCommand(UserProfileCreatedFailEvent event) {
        return FailCommand.builder()
                .userId(event.getUserId())
                .build();
    }

    public static IntrospectCommand toIntrospectCommand(IntrospectRequestDTO dto) {
        return IntrospectCommand.builder().accessToken(dto.getAccessToken()).build();
    }

    public static IntrospectResponseDTO toIntrospectResponse(IntrospectResult result) {
        return IntrospectResponseDTO.builder()
                .isValid(result.isValid())
                .message(result.getMessage())
                .build();
    }

    public static ChangePasswordCommand toChangePasswordCommand(ChangePasswordRequestDTO dto, String token) {
        return ChangePasswordCommand.builder()
                .token(token)
                .oldPassword(dto.getOldPassword())
                .newPassword(dto.getNewPassword())
                .build();
    }
}
