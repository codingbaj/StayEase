package com.codder.stayease.repository;

import com.codder.stayease.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent,Long> {
    // Get all rents belonging to a particular user
    List<Rent> findByTenant_User_Id(long userId);

    // Get one rent belonging to a particular user
    Optional<Rent> findByIdAndTenant_User_Id(long rentId, long userId);
}
