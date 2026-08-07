package com.codder.stayease.repository;

import com.codder.stayease.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor,Long> {
}
