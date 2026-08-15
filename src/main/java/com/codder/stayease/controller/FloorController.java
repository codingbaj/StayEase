package com.codder.stayease.controller;

import com.codder.stayease.dto.FloorRequest;
import com.codder.stayease.dto.FloorResponse;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.FloorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService service;


    // =========================
    // ADD FLOOR
    // =========================

    @PostMapping("/add")
    public ApiResponse addFloor(
            @RequestBody FloorRequest request) {

        FloorResponse floor = service.addFloor(request);

        return new ApiResponse(
                true,
                "Successfully Floor record added!",
                floor
        );
    }


    // =========================
    // GET ALL FLOORS
    // =========================

    @GetMapping("/all")
    public ApiResponse getAllFloor() {

        List<FloorResponse> floors =
                service.getAllFloor();

        return new ApiResponse(
                true,
                "Successfully Fetched all floor!",
                floors
        );
    }


    // =========================
    // GET FLOOR BY ID
    // =========================

    @GetMapping("/{id}")
    public ApiResponse getById(
            @PathVariable long id) {

        FloorResponse floor =
                service.getById(id);

        return new ApiResponse(
                true,
                "Floor successfully fetched!",
                floor
        );
    }


    // =========================
    // UPDATE FLOOR
    // =========================

    @PutMapping("/update/{id}")
    public ApiResponse updateFloor(
            @PathVariable long id,
            @RequestBody FloorRequest request) {

        FloorResponse floor =
                service.updateFloor(id, request);

        return new ApiResponse(
                true,
                "Floor Successfully Updated!",
                floor
        );
    }


    // =========================
    // DELETE FLOOR
    // =========================

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteFloor(
            @PathVariable long id) {

        service.deleteFloor(id);

        return new ApiResponse(
                true,
                "Floor Successfully Deleted!",
                null
        );
    }
}