package com.codder.stayease.repository;

import com.codder.stayease.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AllocationRepository extends JpaRepository<Allocation, Long> {

    List<Allocation> findByTenant_User_Id(long userId);

    Optional<Allocation> findByIdAndTenant_User_Id(long allocationId, long userId);
}
