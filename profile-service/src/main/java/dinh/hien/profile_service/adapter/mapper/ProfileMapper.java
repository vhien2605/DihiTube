package dinh.hien.profile_service.adapter.mapper;

import dinh.hien.profile_service.adapter.dto.response.profile.ProfileResponseDTO;
import dinh.hien.profile_service.adapter.event.KafkaUserCreatedEvent;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileCommand;
import dinh.hien.profile_service.application.usecase.readprofile.ReadProfileResult;
import org.springframework.context.annotation.Profile;

public class ProfileMapper {
    public static CreateProfileCommand toCreateProfileCommand(KafkaUserCreatedEvent userCreatedEvent) {
        return CreateProfileCommand.builder()
                .userId(userCreatedEvent.getUserId())
                .displayName(userCreatedEvent.getDisplayName())
                .phoneNumber(userCreatedEvent.getPhoneNumber())
                .build();
    }

    public static ProfileResponseDTO toReadProfileResponseDTO(ReadProfileResult readProfileResult) {
        return ProfileResponseDTO.builder()
                .id(readProfileResult.getId())
                .displayName(readProfileResult.getDisplayName())
                .phoneNumber(readProfileResult.getPhoneNumber())
                .avatarUrl(readProfileResult.getAvatarUrl())
                .subscription(readProfileResult.getSubscription())
                .userId(readProfileResult.getUserId())
                .build();
    }
}
