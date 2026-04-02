package dinh.hien.profile_service.adapter.api;

import dinh.hien.profile_service.adapter.dto.request.SubscriptionRequestDTO;
import dinh.hien.profile_service.adapter.dto.response.ApiSuccessResponse;
import dinh.hien.profile_service.adapter.dto.response.profile.ProfileResponseDTO;
import dinh.hien.profile_service.adapter.mapper.ProfileMapper;
import dinh.hien.profile_service.application.usecase.readprofile.ReadProfileUseCase;
import dinh.hien.profile_service.application.usecase.subscription.SubscriptionCheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ReadProfileUseCase readProfileUseCase;
    private final SubscriptionCheckoutUseCase subscriptionCheckoutUseCase;

    @GetMapping("/my-profile")
    public ResponseEntity<ApiSuccessResponse<ProfileResponseDTO>> myProfile() {
        var result = readProfileUseCase.readProfile();
        ApiSuccessResponse<ProfileResponseDTO> response =
                ApiSuccessResponse.<ProfileResponseDTO>builder()
                        .message("get profile")
                        .data(ProfileMapper.toReadProfileResponseDTO(result))
                        .build();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/subscription")
    public ResponseEntity<ApiSuccessResponse<String>> subscription(
            @RequestBody SubscriptionRequestDTO subscriptionRequestDTO
    ) {
        ApiSuccessResponse<String> response =
                ApiSuccessResponse.<String>builder()
                        .message("upgrade subscription")
                        .data(subscriptionCheckoutUseCase.subscriptionCheckout(
                                ProfileMapper.toSubscriptionCommand(subscriptionRequestDTO))
                        )
                        .build();
        return ResponseEntity.ok(response);
    }
}
