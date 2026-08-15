package com.codder.stayease.controller;

import com.codder.stayease.dto.RentRequest;
import com.codder.stayease.dto.RentResponse;
import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService service;


    // =====================================================
    // ADD RENT
    // =====================================================

    @PostMapping("/add")
    public ApiResponse addRent(
            @RequestBody RentRequest request) {

        RentResponse rent =
                service.addRent(request);

        return new ApiResponse(
                true,
                "Rent Successfully Added!",
                rent
        );
    }


    // =====================================================
    // GET ALL RENTS
    // =====================================================

    @GetMapping("/all")
    public ApiResponse getAllRent() {

        List<RentResponse> rents =
                service.getAllRent();

        return new ApiResponse(
                true,
                "All Rents Fetched!",
                rents
        );
    }


    // =====================================================
    // GET RENT BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ApiResponse getRentById(
            @PathVariable long id) {

        RentResponse rent =
                service.getRentById(id);

        return new ApiResponse(
                true,
                "Rent Successfully Fetched!",
                rent
        );
    }


    // =====================================================
    // GET MY RENTS
    // =====================================================

    @GetMapping("/my")
    public ApiResponse getMyRents(
            Authentication authentication) {

        User user =
                (User) authentication.getPrincipal();

        List<RentResponse> rents =
                service.getMyRents(
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Your Rents Fetched!",
                rents
        );
    }


    // =====================================================
    // GET MY RENT BY ID
    // =====================================================

    @GetMapping("/my/{id}")
    public ApiResponse getMyRentById(
            @PathVariable long id,
            Authentication authentication) {

        User user =
                (User) authentication.getPrincipal();

        RentResponse rent =
                service.getMyRentById(
                        id,
                        user.getId()
                );

        return new ApiResponse(
                true,
                "Your Rent Successfully Fetched!",
                rent
        );
    }


    // =====================================================
    // UPDATE RENT
    // =====================================================

    @PutMapping("/update/{id}")
    public ApiResponse updateRentById(
            @PathVariable long id,
            @RequestBody RentRequest request) {

        RentResponse rent =
                service.updateRentById(
                        id,
                        request
                );

        return new ApiResponse(
                true,
                "Rent Successfully Updated!",
                rent
        );
    }


    // =====================================================
    // DELETE RENT
    // =====================================================

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteRentById(
            @PathVariable long id) {

        service.deleteRentById(id);

        return new ApiResponse(
                true,
                "Rent Successfully Deleted!",
                null
        );
    }
}