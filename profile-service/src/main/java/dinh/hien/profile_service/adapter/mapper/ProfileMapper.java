package dinh.hien.profile_service.adapter.mapper;

import dinh.hien.profile_service.adapter.dto.request.SubscriptionRequestDTO;
import dinh.hien.profile_service.adapter.dto.request.UpdateProfileRequestDTO;
import dinh.hien.profile_service.adapter.dto.response.profile.ProfileResponseDTO;
import dinh.hien.profile_service.adapter.event.KafkaUserCreatedEvent;
import dinh.hien.profile_service.adapter.event.SubscriptionPaymentSuccessEvent;
import dinh.hien.profile_service.application.usecase.profile.CreateProfileCommand;
import dinh.hien.profile_service.application.usecase.readprofile.ReadProfileResult;
import dinh.hien.profile_service.application.usecase.subscription.SubscriptionCommand;
import dinh.hien.profile_service.application.usecase.update.UpdateProfileCommand;
import dinh.hien.profile_service.domain.profile.UserId;
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

    public static SubscriptionCommand toSubscriptionCommand(SubscriptionRequestDTO subscriptionRequestDTO) {
        return SubscriptionCommand.builder()
                .subscriptionType(subscriptionRequestDTO.getSubscriptionType())
                .build();
    }

    public static SubscriptionCommand toSubscriptionCommand(SubscriptionPaymentSuccessEvent event) {
        return SubscriptionCommand.builder()
                .userId(UserId.of(event.getUserId()))
                .subscriptionType(event.getSubscriptionType())
                .build();
    }

    public static UpdateProfileCommand toUpdateProfileCommand(UpdateProfileRequestDTO updateProfileRequestDTO) {
        return UpdateProfileCommand.builder()
                .displayName(updateProfileRequestDTO.getDisplayName())
                .avatarUrl(updateProfileRequestDTO.getAvatarUrl())
                .phoneNumber(updateProfileRequestDTO.getPhoneNumber())
                .build();
    }
}
