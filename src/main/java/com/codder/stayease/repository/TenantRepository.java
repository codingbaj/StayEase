package com.codder.stayease.repository;

import com.codder.stayease.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant,Long> {
    Optional<Tenant> findByUserId(long userId);
}
