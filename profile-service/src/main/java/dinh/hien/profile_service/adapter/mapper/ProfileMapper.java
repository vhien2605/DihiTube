package dinh.hien.profile_service.adapter.mapper;

import dinh.hien.profile_service.adapter.event.UserCreatedEvent;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileCommand;

public class ProfileMapper {
    public static CreateProfileCommand toCreateProfileCommand(UserCreatedEvent userCreatedEvent){
        return CreateProfileCommand.builder()
                .displayName(userCreatedEvent.getDisplayName())
                .phoneNumber(userCreatedEvent.getPhoneNumber())
                .build();
    }
}
