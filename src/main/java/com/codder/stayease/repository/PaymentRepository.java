package com.codder.stayease.repository;

import com.codder.stayease.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByRent_Tenant_User_Id(long userId);

    // One payment only if it belongs to that tenant/user
    Optional<Payment> findByIdAndRent_Tenant_User_Id(
            long paymentId,
            long userId
    );
}
