package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.AllocationRequest;
import com.codder.stayease.dto.AllocationResponse;
import com.codder.stayease.entity.Allocation;
import com.codder.stayease.entity.Bed;
import com.codder.stayease.entity.Building;
import com.codder.stayease.entity.Floor;
import com.codder.stayease.entity.Room;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.repository.AllocationRepository;
import com.codder.stayease.repository.BedRepository;
import com.codder.stayease.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepo;

    @Autowired
    private TenantRepository tenantRepo;

    @Autowired
    private BedRepository bedRepo;


    // =========================================================
    // ADD ALLOCATION
    // =========================================================

    @Transactional
    public AllocationResponse addAllocation(AllocationRequest request) {

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant not Found!")
                );

        Bed bed = bedRepo.findById(request.getBedId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bed not Found!")
                );


        Allocation allocation = new Allocation();

        allocation.setCheckInDate(request.getCheckInDate());
        allocation.setCheckOutDate(request.getCheckOutDate());
        allocation.setStatus(request.getStatus());

        allocation.setTenant(tenant);
        allocation.setBed(bed);


        Allocation savedAllocation =
                allocationRepo.save(allocation);


        return convertToResponse(savedAllocation);
    }


    // =========================================================
    // GET ALL ALLOCATIONS
    // =========================================================

    @Transactional(readOnly = true)
    public List<AllocationResponse> getAllAllocation() {

        return allocationRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    // =========================================================
    // GET ALLOCATION BY ID
    // =========================================================

    @Transactional(readOnly = true)
    public AllocationResponse getAllocationById(long id) {

        Allocation allocation =
                allocationRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Allocation not Found!"
                                )
                        );

        return convertToResponse(allocation);
    }


    // =========================================================
    // GET LOGGED-IN TENANT ALLOCATIONS
    // =========================================================

    @Transactional(readOnly = true)
    public List<AllocationResponse> getMyAllocations(long userId) {

        return allocationRepo
                .findByTenant_User_Id(userId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    // =========================================================
    // GET LOGGED-IN TENANT ALLOCATION BY ID
    // =========================================================

    @Transactional(readOnly = true)
    public AllocationResponse getMyAllocationById(
            long id,
            long userId
    ) {

        Allocation allocation =
                allocationRepo
                        .findByIdAndTenant_User_Id(id, userId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Allocation not Found for this Tenant!"
                                )
                        );

        return convertToResponse(allocation);
    }


    // =========================================================
    // UPDATE ALLOCATION
    // =========================================================

    @Transactional
    public AllocationResponse updateAllocationById(
            long id,
            AllocationRequest request
    ) {

        Allocation allocation =
                allocationRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Allocation not Found!"
                                )
                        );


        Tenant tenant =
                tenantRepo.findById(request.getTenantId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tenant not Found!"
                                )
                        );


        Bed bed =
                bedRepo.findById(request.getBedId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Bed not Found!"
                                )
                        );


        allocation.setCheckInDate(
                request.getCheckInDate()
        );

        allocation.setCheckOutDate(
                request.getCheckOutDate()
        );

        allocation.setStatus(
                request.getStatus()
        );

        allocation.setTenant(tenant);

        allocation.setBed(bed);


        Allocation savedAllocation =
                allocationRepo.save(allocation);


        return convertToResponse(savedAllocation);
    }


    // =========================================================
    // DELETE ALLOCATION
    // =========================================================

    @Transactional
    public void deleteAllocationById(long id) {

        Allocation allocation =
                allocationRepo.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Allocation not Found!"
                                )
                        );

        allocationRepo.delete(allocation);
    }


    // =========================================================
    // CONVERT ENTITY → RESPONSE DTO
    // =========================================================

    private AllocationResponse convertToResponse(
            Allocation allocation
    ) {

        Tenant tenant = allocation.getTenant();

        Bed bed = allocation.getBed();

        String tenantName = null;
        String tenantEmail = null;

        long tenantId = 0;

        if (tenant != null) {

            tenantId = tenant.getId();

            if (tenant.getUser() != null) {

                tenantName =
                        tenant.getUser().getName();

                tenantEmail =
                        tenant.getUser().getEmail();
            }
        }


        long bedId = 0;
        String bedNumber = null;

        long roomId = 0;
        String roomNumber = null;

        long floorId = 0;
        int floorNumber = 0;

        long buildingId = 0;
        String buildingCode = null;
        String buildingName = null;


        if (bed != null) {

            bedId = bed.getId();

            bedNumber =
                    bed.getBedNumber();


            Room room = bed.getRoom();

            if (room != null) {

                roomId = room.getId();

                roomNumber =
                        room.getRoomNumber();


                Floor floor =
                        room.getFloor();

                if (floor != null) {

                    floorId = floor.getId();

                    floorNumber =
                            floor.getFloorNumber();


                    Building building =
                            floor.getBuilding();

                    if (building != null) {

                        buildingId =
                                building.getId();

                        buildingCode =
                                building.getBuildingCode();

                        buildingName =
                                building.getBuildingName();
                    }
                }
            }
        }


        return new AllocationResponse(

                allocation.getId(),

                allocation.getCheckInDate(),

                allocation.getCheckOutDate(),

                allocation.getStatus(),

                tenantId,

                tenantName,

                tenantEmail,

                bedId,

                bedNumber,

                roomId,

                roomNumber,

                floorId,

                floorNumber,

                buildingId,

                buildingCode,

                buildingName
        );
    }
}