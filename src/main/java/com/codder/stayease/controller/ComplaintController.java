package com.codder.stayease.controller;

import com.codder.stayease.dto.ComplaintRequest;
import com.codder.stayease.entity.Complaint;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService service;


    // ADD COMPLAINT
    @PostMapping("/add")
    public ApiResponse addComplaint(
            @RequestBody ComplaintRequest request) {

        Complaint complaint = service.addComplaint(request);

        return new ApiResponse(
                true,
                "Complaint Successfully Added!",
                complaint
        );
    }


    // GET ALL COMPLAINTS
    @GetMapping("/all")
    public ApiResponse getAllComplaint() {

        List<Complaint> complaints =
                service.getAllComplaint();

        return new ApiResponse(
                true,
                "All Complaints Fetched!",
                complaints
        );
    }


    // GET COMPLAINT BY ID
    @GetMapping("/{id}")
    public ApiResponse getComplaintById(
            @PathVariable long id) {

        Complaint complaint =
                service.getComplaintById(id);

        return new ApiResponse(
                true,
                "Complaint Successfully Fetched!",
                complaint
        );
    }


    // UPDATE COMPLAINT
    @PutMapping("/update/{id}")
    public ApiResponse updateComplaintById(
            @PathVariable long id,
            @RequestBody ComplaintRequest request) {

        Complaint complaint =
                service.updateComplaintById(id, request);

        return new ApiResponse(
                true,
                "Complaint Successfully Updated!",
                complaint
        );
    }


    // DELETE COMPLAINT
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteComplaintById(
            @PathVariable long id) {

        service.deleteComplaintById(id);

        return new ApiResponse(
                true,
                "Complaint Successfully Deleted!",
                null
        );
    }
}