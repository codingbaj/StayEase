package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.RentRequest;
import com.codder.stayease.entity.Rent;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.repository.RentRepository;
import com.codder.stayease.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepo;

    @Autowired
    private TenantRepository tenantRepo;


    // =====================================================
    // ADD RENT
    // ADMIN / STAFF
    // =====================================================

    public Rent addRent(RentRequest request) {

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant Not Found!"
                        ));

        Rent rent = new Rent();

        rent.setMonth(request.getMonth());
        rent.setYear(request.getYear());
        rent.setDueDate(request.getDueDate());

        rent.setRoomRent(request.getRoomRent());
        rent.setElectricityBill(request.getElectricityBill());
        rent.setWaterBill(request.getWaterBill());
        rent.setMaintenanceCharge(
                request.getMaintenanceCharge()
        );

        // No late fine when rent is created
        rent.setLateFine(0);

        // Initial total
        double totalAmount =
                request.getRoomRent()
                        + request.getElectricityBill()
                        + request.getWaterBill()
                        + request.getMaintenanceCharge();

        rent.setTotalAmount(totalAmount);

        rent.setStatus(request.getStatus());

        rent.setTenant(tenant);

        return rentRepo.save(rent);
    }


    // =====================================================
    // GET ALL RENTS
    // ADMIN / STAFF
    // =====================================================

    public List<Rent> getAllRent() {

        return rentRepo.findAll();
    }


    // =====================================================
    // GET RENT BY ID
    // ADMIN / STAFF
    // =====================================================

    public Rent getRentById(long id) {

        return rentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));
    }


    // =====================================================
    // GET LOGGED-IN TENANT'S RENTS
    // TENANT
    // =====================================================

    public List<Rent> getMyRents(long userId) {

        return rentRepo.findByTenant_User_Id(userId);
    }


    // =====================================================
    // GET ONE RENT OF LOGGED-IN TENANT
    // TENANT
    // =====================================================

    public Rent getMyRentById(
            long rentId,
            long userId) {

        return rentRepo
                .findByIdAndTenant_User_Id(
                        rentId,
                        userId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found for this Tenant!"
                        ));
    }


    // =====================================================
    // UPDATE RENT
    // ADMIN / STAFF
    // =====================================================

    public Rent updateRentById(
            long id,
            RentRequest request) {

        Rent rent = rentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));

        Tenant tenant = tenantRepo.findById(
                        request.getTenantId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant Not Found!"
                        ));


        rent.setMonth(request.getMonth());
        rent.setYear(request.getYear());
        rent.setDueDate(request.getDueDate());

        rent.setRoomRent(request.getRoomRent());
        rent.setElectricityBill(
                request.getElectricityBill()
        );
        rent.setWaterBill(
                request.getWaterBill()
        );
        rent.setMaintenanceCharge(
                request.getMaintenanceCharge()
        );

        rent.setStatus(request.getStatus());


        // Keep existing late fine
        double totalAmount =
                request.getRoomRent()
                        + request.getElectricityBill()
                        + request.getWaterBill()
                        + request.getMaintenanceCharge()
                        + rent.getLateFine();

        rent.setTotalAmount(totalAmount);

        rent.setTenant(tenant);

        return rentRepo.save(rent);
    }


    // =====================================================
    // DELETE RENT
    // ADMIN / STAFF
    // =====================================================

    public void deleteRentById(long id) {

        Rent rent = rentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));

        rentRepo.delete(rent);
    }
}