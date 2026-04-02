package com.hien.payment_service.infra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JpaPayment {
    @Id
    @NotBlank
    private String id;

    @NotNull
    @Positive
    private Long amount;

    @NotBlank
    @Size(max = 10)
    private String currency;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotBlank
    private String status;

    @NotBlank
    private String userId;

    @Size(max = 100)
    private String transactionRef;

    @NotBlank
    private String subscriptionType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
