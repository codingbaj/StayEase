package com.codder.stayease.controller;

import com.codder.stayease.dto.AllocationRequest;
import com.codder.stayease.entity.Allocation;
import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocation")
public class AllocationController {

    @Autowired private AllocationService service;

    @PostMapping("/add")
    public ApiResponse addAllocation(@RequestBody AllocationRequest request) {
        return new ApiResponse(true, "Allocation Successfully Added!", service.addAllocation(request));
    }

    @GetMapping("/all")
    public ApiResponse getAllAllocation() {
        List<Allocation> allocations = service.getAllAllocation();
        return new ApiResponse(true, "All Allocations Fetched!", allocations);
    }

    @GetMapping("/{id}")
    public ApiResponse getAllocationById(@PathVariable long id) {
        return new ApiResponse(true, "Allocation Successfully Fetched!", service.getAllocationById(id));
    }

    @GetMapping("/my")
    public ApiResponse getMyAllocations(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Your Allocations Fetched!", service.getMyAllocations(user.getId()));
    }

    @GetMapping("/my/{id}")
    public ApiResponse getMyAllocationById(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Your Allocation Successfully Fetched!", service.getMyAllocationById(id, user.getId()));
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateAllocationById(@PathVariable long id, @RequestBody AllocationRequest request) {
        return new ApiResponse(true, "Allocation Successfully Updated!", service.updateAllocationById(id, request));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteAllocationById(@PathVariable long id) {
        service.deleteAllocationById(id);
        return new ApiResponse(true, "Allocation Successfully Deleted!", null);
    }
}
