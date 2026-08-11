package com.codder.stayease.repository;

import com.codder.stayease.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    List<Visitor> findByTenant_User_Id(long userId);

    Optional<Visitor> findByIdAndTenant_User_Id(long visitorId, long userId);
}
