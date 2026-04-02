package com.hien.payment_service.infra.repository;

import com.hien.payment_service.infra.model.JpaPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaPaymentRepository extends JpaRepository<JpaPayment, String> {
    @Query("""
            SELECT p FROM JpaPayment p
            WHERE p.transactionRef = :transactionRef
            """)
    Optional<JpaPayment> findByTransactionRef(String transactionRef);
}
