package dinh.hien.profile_service.infra.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum InfraError {
    SUBSCRIPTION_NOT_FOUND(1000,"Subscription not found, please try later")
    ;
    private final int code;
    private final String message;
}
