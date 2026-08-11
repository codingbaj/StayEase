package com.codder.stayease.controller;

import com.codder.stayease.dto.RentRequest;
import com.codder.stayease.entity.Rent;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    private RentService service;


    @PostMapping("/add")
    public ApiResponse addRent(@RequestBody RentRequest request) {

        Rent rent = service.addRent(request);

        return new ApiResponse(
                true,
                "Rent Successfully Added!",
                rent
        );
    }


    @GetMapping("/all")
    public ApiResponse getAllRent() {

        List<Rent> rents = service.getAllRent();

        return new ApiResponse(
                true,
                "All Rents Fetched!",
                rents
        );
    }


    @GetMapping("/{id}")
    public ApiResponse getRentById(@PathVariable long id) {

        Rent rent = service.getRentById(id);

        return new ApiResponse(
                true,
                "Rent Successfully Fetched!",
                rent
        );
    }


    @PutMapping("/update/{id}")
    public ApiResponse updateRentById(
            @PathVariable long id,
            @RequestBody RentRequest request) {

        Rent rent = service.updateRentById(id, request);

        return new ApiResponse(
                true,
                "Rent Successfully Updated!",
                rent
        );
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteRentById(@PathVariable long id) {

        service.deleteRentById(id);

        return new ApiResponse(
                true,
                "Rent Successfully Deleted!",
                null
        );
    }
}