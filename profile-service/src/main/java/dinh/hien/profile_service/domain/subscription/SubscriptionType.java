package dinh.hien.profile_service.domain.subscription;

import lombok.Getter;

@Getter
public enum SubscriptionType {
    STANDARD(0),
    PREMIUM(500000);
    private final long price;

    SubscriptionType(long price) {
        this.price = price;
    }
}
