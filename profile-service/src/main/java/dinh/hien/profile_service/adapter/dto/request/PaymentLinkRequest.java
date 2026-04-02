package dinh.hien.profile_service.adapter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLinkRequest implements Serializable {
    private BigDecimal amount;
    private String currency;
    private String description;
}

