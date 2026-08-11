package com.codder.stayease.controller;

import com.codder.stayease.dto.AllocationRequest;
import com.codder.stayease.entity.Allocation;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/allocation")
public class AllocationController {

    @Autowired
    private AllocationService service;


    @PostMapping("/add")
    public ApiResponse addAllocation(@RequestBody AllocationRequest request) {

        Allocation allocation = service.addAllocation(request);

        return new ApiResponse(
                true,
                "Allocation Successfully Added!",
                allocation
        );
    }


    @GetMapping("/all")
    public ApiResponse getAllAllocation() {

        List<Allocation> allocations = service.getAllAllocation();

        return new ApiResponse(
                true,
                "All Allocations Fetched!",
                allocations
        );
    }


    @GetMapping("/{id}")
    public ApiResponse getAllocationById(@PathVariable long id) {

        Allocation allocation = service.getAllocationById(id);

        return new ApiResponse(
                true,
                "Allocation Successfully Fetched!",
                allocation
        );
    }


    @PutMapping("/update/{id}")
    public ApiResponse updateAllocationById(
            @PathVariable long id,
            @RequestBody AllocationRequest request) {

        Allocation allocation =
                service.updateAllocationById(id, request);

        return new ApiResponse(
                true,
                "Allocation Successfully Updated!",
                allocation
        );
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteAllocationById(@PathVariable long id) {

        service.deleteAllocationById(id);

        return new ApiResponse(
                true,
                "Allocation Successfully Deleted!",
                null
        );
    }
}