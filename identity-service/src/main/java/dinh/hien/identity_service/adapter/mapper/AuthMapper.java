package dinh.hien.identity_service.adapter.mapper;

import dinh.hien.identity_service.adapter.dto.request.LoginRequestDTO;
import dinh.hien.identity_service.application.usecase.login.LoginCommand;

public class AuthMapper {
    public static LoginCommand toLoginCommand(LoginRequestDTO dto){
        return LoginCommand.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }
}
