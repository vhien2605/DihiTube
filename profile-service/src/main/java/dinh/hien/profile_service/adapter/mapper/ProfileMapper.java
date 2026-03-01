package dinh.hien.profile_service.adapter.mapper;

import dinh.hien.profile_service.adapter.event.KafkaUserCreatedEvent;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileCommand;

public class ProfileMapper {
    public static CreateProfileCommand toCreateProfileCommand(KafkaUserCreatedEvent userCreatedEvent){
        return CreateProfileCommand.builder()
                .userId(userCreatedEvent.getUserId())
                .displayName(userCreatedEvent.getDisplayName())
                .phoneNumber(userCreatedEvent.getPhoneNumber())
                .build();
    }
}
