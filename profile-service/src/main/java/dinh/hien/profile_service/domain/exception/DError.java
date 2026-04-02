package dinh.hien.profile_service.domain.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DError {
    PHONE_NUMBER_INVALID(1001, "Your phone number is invalid"),
    PROFILE_ID_INVALID(1002, "Your id is invalid"),
    USER_ID_INVALID(1003, "Your user id is invalid"),
    SUBSCRIPTION_ID_INVALID(1004, "Your subscription id is invalid"),
    PROFILE_NOT_EXISTED(1005, "Your profile does not exist"),
    SUBSCRIPTION_NOT_EXISTED(1006, "Your subscription does not exist"),
    PAYMENT_LINK_FAILED(1007, "Failed to get payment link from payment service"),
    ;
    private final int code;
    private final String message;
}
