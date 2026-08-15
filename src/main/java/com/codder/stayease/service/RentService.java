package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.RentRequest;
import com.codder.stayease.dto.RentResponse;
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
    // CONVERT RENT ENTITY -> RENT RESPONSE
    // =====================================================

    private RentResponse convertToResponse(Rent rent) {

        Tenant tenant = rent.getTenant();

        long tenantId = 0;
        String tenantName = null;
        String tenantEmail = null;

        if (tenant != null) {

            tenantId = tenant.getId();

            if (tenant.getUser() != null) {
                tenantName = tenant.getUser().getName();
                tenantEmail = tenant.getUser().getEmail();
            }
        }

        return new RentResponse(
                rent.getId(),
                rent.getMonth(),
                rent.getYear(),
                rent.getDueDate(),
                rent.getRoomRent(),
                rent.getElectricityBill(),
                rent.getWaterBill(),
                rent.getMaintenanceCharge(),
                rent.getLateFine(),
                rent.getTotalAmount(),
                rent.getStatus(),
                tenantId,
                tenantName,
                tenantEmail
        );
    }


    // =====================================================
    // ADD RENT
    // =====================================================

    public RentResponse addRent(RentRequest request) {

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

        // New rent starts with zero late fine
        rent.setLateFine(0);

        double totalAmount =
                request.getRoomRent()
                        + request.getElectricityBill()
                        + request.getWaterBill()
                        + request.getMaintenanceCharge();

        rent.setTotalAmount(totalAmount);

        rent.setStatus(request.getStatus());

        rent.setTenant(tenant);

        Rent savedRent = rentRepo.save(rent);

        return convertToResponse(savedRent);
    }


    // =====================================================
    // GET ALL RENTS
    // =====================================================

    public List<RentResponse> getAllRent() {

        return rentRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =====================================================
    // GET RENT BY ID
    // =====================================================

    public RentResponse getRentById(long id) {

        Rent rent = rentRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found!"
                        ));

        return convertToResponse(rent);
    }


    // =====================================================
    // GET LOGGED-IN TENANT'S RENTS
    // =====================================================

    public List<RentResponse> getMyRents(long userId) {

        return rentRepo.findByTenant_User_Id(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =====================================================
    // GET ONE RENT OF LOGGED-IN TENANT
    // =====================================================

    public RentResponse getMyRentById(
            long rentId,
            long userId) {

        Rent rent = rentRepo
                .findByIdAndTenant_User_Id(
                        rentId,
                        userId
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Rent Not Found for this Tenant!"
                        ));

        return convertToResponse(rent);
    }


    // =====================================================
    // UPDATE RENT
    // =====================================================

    public RentResponse updateRentById(
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

        Rent savedRent = rentRepo.save(rent);

        return convertToResponse(savedRent);
    }


    // =====================================================
    // DELETE RENT
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