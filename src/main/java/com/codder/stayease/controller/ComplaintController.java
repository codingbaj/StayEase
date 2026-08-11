package com.codder.stayease.controller;

import com.codder.stayease.dto.ComplaintRequest;
import com.codder.stayease.entity.Complaint;
import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired private ComplaintService service;

    @PostMapping("/add")
    public ApiResponse addComplaint(@RequestBody ComplaintRequest request) {
        Complaint complaint = service.addComplaint(request);
        return new ApiResponse(true, "Complaint Successfully Added!", complaint);
    }

    @PostMapping("/my/add")
    public ApiResponse addMyComplaint(@RequestBody ComplaintRequest request,
                                       Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Complaint complaint = service.addComplaintForTenant(request, user.getId());
        return new ApiResponse(true, "Complaint Successfully Added!", complaint);
    }

    @GetMapping("/all")
    public ApiResponse getAllComplaint() {
        List<Complaint> complaints = service.getAllComplaint();
        return new ApiResponse(true, "All Complaints Fetched!", complaints);
    }

    @GetMapping("/{id}")
    public ApiResponse getComplaintById(@PathVariable long id) {
        return new ApiResponse(true, "Complaint Successfully Fetched!", service.getComplaintById(id));
    }

    @GetMapping("/my")
    public ApiResponse getMyComplaints(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Your Complaints Fetched!", service.getMyComplaints(user.getId()));
    }

    @GetMapping("/my/{id}")
    public ApiResponse getMyComplaintById(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Your Complaint Successfully Fetched!", service.getMyComplaintById(id, user.getId()));
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateComplaintById(@PathVariable long id,
                                           @RequestBody ComplaintRequest request) {
        return new ApiResponse(true, "Complaint Successfully Updated!", service.updateComplaintById(id, request));
    }

    @PutMapping("/my/update/{id}")
    public ApiResponse updateMyComplaint(@PathVariable long id,
                                         @RequestBody ComplaintRequest request,
                                         Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Complaint Successfully Updated!", service.updateMyComplaint(id, request, user.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteComplaintById(@PathVariable long id) {
        service.deleteComplaintById(id);
        return new ApiResponse(true, "Complaint Successfully Deleted!", null);
    }

    @DeleteMapping("/my/delete/{id}")
    public ApiResponse deleteMyComplaint(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        service.deleteMyComplaint(id, user.getId());
        return new ApiResponse(true, "Complaint Successfully Deleted!", null);
    }
}
