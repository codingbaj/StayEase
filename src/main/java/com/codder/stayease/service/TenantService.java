package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.TenantRequest;
import com.codder.stayease.dto.TenantResponse;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.entity.User;
import com.codder.stayease.repository.TenantRepository;
import com.codder.stayease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TenantRepository tenantRepo;


    // =========================
    // ADD TENANT
    // =========================

    @Transactional
    public TenantResponse addTenant(TenantRequest request) {

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not Found!"
                        )
                );

        Tenant tenant = new Tenant();

        tenant.setAadhaarNo(request.getAadhaarNo());
        tenant.setAddress(request.getAddress());
        tenant.setGuardianName(request.getGuardianName());
        tenant.setGuardianPhone(request.getGuardianPhone());
        tenant.setOccupation(request.getOccupation());

        tenant.setUser(user);

        user.setTenant(tenant);

        Tenant savedTenant = tenantRepo.save(tenant);

        return convertToResponse(savedTenant);
    }


    // =========================
    // GET ALL TENANTS
    // =========================

    public List<TenantResponse> getAllTenant() {

        return tenantRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================
    // GET TENANT BY ID
    // =========================

    public TenantResponse getTenantById(long id) {

        Tenant tenant = tenantRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant not Found!"
                        )
                );

        return convertToResponse(tenant);
    }


    // =========================
    // GET LOGGED-IN TENANT
    // =========================

    public Tenant getTenantEntityByUserId(long userId) {

        return tenantRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant not Found for this User!"
                        )
                );
    }


    // =========================
    // UPDATE TENANT
    // =========================

    @Transactional
    public TenantResponse updateTenantById(
            long id,
            TenantRequest request
    ) {

        Tenant tenant = tenantRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant Not Found!"
                        )
                );

        User newUser = userRepo.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User Not Found!"
                        )
                );

        User oldUser = tenant.getUser();

        // Remove tenant from old user
        if (
                oldUser != null &&
                        oldUser.getId() != newUser.getId()
        ) {
            oldUser.setTenant(null);
            userRepo.save(oldUser);
        }

        tenant.setUser(newUser);
        newUser.setTenant(tenant);

        tenant.setOccupation(request.getOccupation());
        tenant.setGuardianPhone(request.getGuardianPhone());
        tenant.setGuardianName(request.getGuardianName());
        tenant.setAddress(request.getAddress());
        tenant.setAadhaarNo(request.getAadhaarNo());

        Tenant savedTenant = tenantRepo.save(tenant);

        userRepo.save(newUser);

        return convertToResponse(savedTenant);
    }


    // =========================
    // DELETE TENANT
    // =========================

    @Transactional
    public void deleteTenantById(long id) {

        Tenant tenant = tenantRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant not Found!"
                        )
                );

        User user = tenant.getUser();

        if (user != null) {
            user.setTenant(null);
            userRepo.save(user);
        }

        tenantRepo.delete(tenant);
    }


    // =========================
    // TENANT BY USER ID
    // =========================

    public Tenant getTenantByUserId(long userId) {

        return tenantRepo.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Tenant not Found for this User!"
                        )
                );
    }


    // =========================
    // ENTITY → RESPONSE
    // =========================

    private TenantResponse convertToResponse(Tenant tenant) {

        User user = tenant.getUser();

        if (user == null) {

            return new TenantResponse(
                    tenant.getId(),
                    tenant.getGuardianName(),
                    tenant.getGuardianPhone(),
                    tenant.getAddress(),
                    tenant.getAadhaarNo(),
                    tenant.getOccupation(),
                    0,
                    null,
                    null,
                    null
            );
        }

        return new TenantResponse(
                tenant.getId(),

                tenant.getGuardianName(),
                tenant.getGuardianPhone(),
                tenant.getAddress(),
                tenant.getAadhaarNo(),
                tenant.getOccupation(),

                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}