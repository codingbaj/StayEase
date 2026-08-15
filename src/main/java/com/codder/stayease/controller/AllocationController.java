package com.codder.stayease.controller;

import com.codder.stayease.dto.AllocationRequest;
import com.codder.stayease.dto.AllocationResponse;
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

    @Autowired
    private AllocationService service;


    // =========================================================
    // ADD
    // =========================================================

    @PostMapping("/add")
    public ApiResponse addAllocation(
            @RequestBody AllocationRequest request
    ) {

        AllocationResponse allocation =
                service.addAllocation(request);

        return new ApiResponse(
                true,
                "Allocation Successfully Added!",
                allocation
        );
    }


    // =========================================================
    // GET ALL
    // =========================================================

    @GetMapping("/all")
    public ApiResponse getAllAllocation() {

        List<AllocationResponse> allocations =
                service.getAllAllocation();

        return new ApiResponse(
                true,
                "All Allocations Fetched!",
                allocations
        );
    }


    // =========================================================
    // GET BY ID
    // =========================================================

    @GetMapping("/{id}")
    public ApiResponse getAllocationById(
            @PathVariable long id
    ) {

        AllocationResponse allocation =
                service.getAllocationById(id);

        return new ApiResponse(
                true,
                "Allocation Successfully Fetched!",
                allocation
        );
    }


    // =========================================================
    // GET MY ALLOCATIONS
    // =========================================================

    @GetMapping("/my")
    public ApiResponse getMyAllocations(
            Authentication authentication
    ) {

        User user =
                (User) authentication.getPrincipal();

        List<AllocationResponse> allocations =
                service.getMyAllocations(
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Your Allocations Fetched!",
                allocations
        );
    }


    // =========================================================
    // GET MY ALLOCATION BY ID
    // =========================================================

    @GetMapping("/my/{id}")
    public ApiResponse getMyAllocationById(
            @PathVariable long id,
            Authentication authentication
    ) {

        User user =
                (User) authentication.getPrincipal();

        AllocationResponse allocation =
                service.getMyAllocationById(
                        id,
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Your Allocation Successfully Fetched!",
                allocation
        );
    }


    // =========================================================
    // UPDATE
    // =========================================================

    @PutMapping("/update/{id}")
    public ApiResponse updateAllocationById(
            @PathVariable long id,
            @RequestBody AllocationRequest request
    ) {

        AllocationResponse allocation =
                service.updateAllocationById(
                        id,
                        request
                );

        return new ApiResponse(
                true,
                "Allocation Successfully Updated!",
                allocation
        );
    }


    // =========================================================
    // DELETE
    // =========================================================

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteAllocationById(
            @PathVariable long id
    ) {

        service.deleteAllocationById(id);

        return new ApiResponse(
                true,
                "Allocation Successfully Deleted!",
                null
        );
    }
}