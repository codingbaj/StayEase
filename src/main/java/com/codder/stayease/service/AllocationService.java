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

    @Autowired private AllocationRepository allocationRepo;
    @Autowired private TenantRepository tenantRepo;
    @Autowired private BedRepository bedRepo;

    public Allocation addAllocation(AllocationRequest request) {
        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not Found!"));
        Bed bed = bedRepo.findById(request.getBedId())
                .orElseThrow(() -> new ResourceNotFoundException("Bed not Found!"));
        Allocation allocation = new Allocation();
        allocation.setCheckInDate(request.getCheckInDate());
        allocation.setCheckOutDate(request.getCheckOutDate());
        allocation.setStatus(request.getStatus());
        allocation.setTenant(tenant);
        allocation.setBed(bed);
        return allocationRepo.save(allocation);
    }

    public List<Allocation> getAllAllocation() { return allocationRepo.findAll(); }

    public Allocation getAllocationById(long id) {
        return allocationRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Allocation not Found!"));
    }

    public List<Allocation> getMyAllocations(long userId) {
        return allocationRepo.findByTenant_User_Id(userId);
    }

    public Allocation getMyAllocationById(long id, long userId) {
        return allocationRepo.findByIdAndTenant_User_Id(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Allocation not Found for this Tenant!"));
    }

    public Allocation updateAllocationById(long id, AllocationRequest request) {
        Allocation allocation = getAllocationById(id);
        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not Found!"));
        Bed bed = bedRepo.findById(request.getBedId())
                .orElseThrow(() -> new ResourceNotFoundException("Bed not Found!"));
        allocation.setCheckInDate(request.getCheckInDate());
        allocation.setCheckOutDate(request.getCheckOutDate());
        allocation.setStatus(request.getStatus());
        allocation.setTenant(tenant);
        allocation.setBed(bed);
        return allocationRepo.save(allocation);
    }

    public void deleteAllocationById(long id) { allocationRepo.delete(getAllocationById(id)); }
}
