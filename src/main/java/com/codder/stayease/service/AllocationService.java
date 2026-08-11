package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.AllocationRequest;
import com.codder.stayease.entity.Allocation;
import com.codder.stayease.entity.Bed;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.repository.AllocationRepository;
import com.codder.stayease.repository.BedRepository;
import com.codder.stayease.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {

    @Autowired
    private AllocationRepository allocationRepo;

    @Autowired
    private TenantRepository tenantRepo;

    @Autowired
    private BedRepository bedRepo;


    // ADD ALLOCATION
    public Allocation addAllocation(AllocationRequest request) {

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant not Found!"));

        Bed bed = bedRepo.findById(request.getBedId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bed not Found!"));

        Allocation allocation = new Allocation();

        allocation.setCheckInDate(request.getCheckInDate());
        allocation.setCheckOutDate(request.getCheckOutDate());
        allocation.setStatus(request.getStatus());

        allocation.setTenant(tenant);
        allocation.setBed(bed);

        return allocationRepo.save(allocation);
    }


    // GET ALL ALLOCATIONS
    public List<Allocation> getAllAllocation() {

        return allocationRepo.findAll();
    }


    // GET ALLOCATION BY ID
    public Allocation getAllocationById(long id) {

        return allocationRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Allocation not Found!"));
    }


    // UPDATE ALLOCATION
    public Allocation updateAllocationById(long id,
                                           AllocationRequest request) {

        Allocation allocation = allocationRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Allocation not Found!"));

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant not Found!"));

        Bed bed = bedRepo.findById(request.getBedId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bed not Found!"));

        allocation.setCheckInDate(request.getCheckInDate());
        allocation.setCheckOutDate(request.getCheckOutDate());
        allocation.setStatus(request.getStatus());

        allocation.setTenant(tenant);
        allocation.setBed(bed);

        return allocationRepo.save(allocation);
    }


    // DELETE ALLOCATION
    public void deleteAllocationById(long id) {

        Allocation allocation = allocationRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Allocation not Found!"));

        allocationRepo.delete(allocation);
    }
}