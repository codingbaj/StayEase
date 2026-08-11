package com.codder.stayease.repository;

import com.codder.stayease.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByTenant_User_Id(long userId);

    Optional<Complaint> findByIdAndTenant_User_Id(long complaintId, long userId);
}
