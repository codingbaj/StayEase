package com.codder.stayease.controller;

import com.codder.stayease.dto.VisitorRequest;
import com.codder.stayease.entity.User;
import com.codder.stayease.entity.Visitor;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired private VisitorService service;

    @PostMapping("/add")
    public ApiResponse addVisitor(@RequestBody VisitorRequest request) {
        return new ApiResponse(true, "Visitor Successfully Added!", service.addVisitor(request));
    }

    @PostMapping("/my/add")
    public ApiResponse addMyVisitor(@RequestBody VisitorRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Visitor Successfully Added!", service.addVisitorForTenant(request, user.getId()));
    }

    @GetMapping("/all")
    public ApiResponse getAllVisitor() {
        List<Visitor> visitors = service.getAllVisitor();
        return new ApiResponse(true, "All Visitors Fetched!", visitors);
    }

    @GetMapping("/{id}")
    public ApiResponse getVisitorById(@PathVariable long id) {
        return new ApiResponse(true, "Visitor Successfully Fetched!", service.getVisitorById(id));
    }

    @GetMapping("/my")
    public ApiResponse getMyVisitors(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Your Visitors Fetched!", service.getMyVisitors(user.getId()));
    }

    @GetMapping("/my/{id}")
    public ApiResponse getMyVisitorById(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Your Visitor Successfully Fetched!", service.getMyVisitorById(id, user.getId()));
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateVisitorById(@PathVariable long id, @RequestBody VisitorRequest request) {
        return new ApiResponse(true, "Visitor Successfully Updated!", service.updateVisitorById(id, request));
    }

    @PutMapping("/my/update/{id}")
    public ApiResponse updateMyVisitor(@PathVariable long id, @RequestBody VisitorRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return new ApiResponse(true, "Visitor Successfully Updated!", service.updateMyVisitor(id, request, user.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteVisitorById(@PathVariable long id) {
        service.deleteVisitorById(id);
        return new ApiResponse(true, "Visitor Successfully Deleted!", null);
    }

    @DeleteMapping("/my/delete/{id}")
    public ApiResponse deleteMyVisitor(@PathVariable long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        service.deleteMyVisitor(id, user.getId());
        return new ApiResponse(true, "Visitor Successfully Deleted!", null);
    }
}
