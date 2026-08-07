package com.codder.stayease.repository;

import com.codder.stayease.entity.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllocationRepository extends JpaRepository<Allocation,Long> {
}
