package com.codder.stayease.controller;

import com.codder.stayease.dto.TenantRequest;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.entity.User;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tenant")
public class TenantController {

    @Autowired
    private TenantService service;


    // ADD TENANT
    @PostMapping("/add")
    public ApiResponse addTenant(
            @RequestBody TenantRequest request) {

        Tenant tenant = service.addTenant(request);

        return new ApiResponse(
                true,
                tenant.getUser().getName()
                        + " Successfully Added for Tenant!",
                tenant
        );
    }


    // GET ALL TENANTS
    @GetMapping("/all")
    public ApiResponse getAllTenant() {

        List<Tenant> tenants =
                service.getAllTenant();

        return new ApiResponse(
                true,
                "All Tenants Fetched!",
                tenants
        );
    }


    // GET LOGGED-IN TENANT
    @GetMapping("/me")
    public ApiResponse getMyTenant(
            Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        Tenant tenant =
                service.getTenantByUserId(user.getId());

        return new ApiResponse(
                true,
                "Your Tenant Data Successfully Fetched!",
                tenant
        );
    }


    // GET TENANT BY ID
    @GetMapping("/{id}")
    public ApiResponse getTenantById(
            @PathVariable long id) {

        Tenant tenant =
                service.getTenantById(id);

        return new ApiResponse(
                true,
                tenant.getUser().getName()
                        + "'s Data Successfully Fetched!",
                tenant
        );
    }


    // UPDATE TENANT
    @PutMapping("/update/{id}")
    public ApiResponse updateTenantById(
            @PathVariable long id,
            @RequestBody TenantRequest request) {

        Tenant tenant =
                service.updateTenantById(id, request);

        return new ApiResponse(
                true,
                tenant.getUser().getName()
                        + "'s Data Successfully Updated!",
                tenant
        );
    }


    // DELETE TENANT
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteTenantById(
            @PathVariable long id) {

        service.deleteTenantById(id);

        return new ApiResponse(
                true,
                "Tenant Successfully Removed!",
                null
        );
    }
}