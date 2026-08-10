package com.codder.stayease.service;


import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.TenantRequest;
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
    private TenantRepository TenantRepo;

    @Transactional
    public Tenant addTenant(TenantRequest request) {

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("User not Found!"));

        Tenant tenant = new Tenant();
        tenant.setAadhaarNo(request.getAadhaarNo());
        tenant.setAddress(request.getAddress());
        tenant.setGuardianName(request.getGuardianName());
        tenant.setGuardianPhone(request.getGuardianPhone());
        tenant.setOccupation(request.getOccupation());
        tenant.setUser(user);
        user.setTenant(tenant);
        return TenantRepo.save(tenant);
    }

    public List<Tenant> getAllTenant() {

        return TenantRepo.findAll() ;
    }

    public Tenant getTenantById(long id){
        return TenantRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant not Found!"));


    }

    @Transactional
    public Tenant updateTenantById(long id, TenantRequest request) {

        Tenant tenant = TenantRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant Not Found!"));

        User newUser = userRepo.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User Not Found!"));

        User oldUser = tenant.getUser();

        // Remove Tenant from old User
        if (oldUser != null && oldUser.getId() != newUser.getId()) {
            oldUser.setTenant(null);
            userRepo.save(oldUser);
        }

        // Set new relationship
        tenant.setUser(newUser);
        newUser.setTenant(tenant);

        tenant.setOccupation(request.getOccupation());
        tenant.setGuardianPhone(request.getGuardianPhone());
        tenant.setGuardianName(request.getGuardianName());
        tenant.setAddress(request.getAddress());
        tenant.setAadhaarNo(request.getAadhaarNo());

        Tenant savedTenant = TenantRepo.save(tenant);

        userRepo.save(newUser);

        return savedTenant;
    }

    @Transactional
    public void deleteTenantById(long id){
        Tenant tenant = TenantRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tenant not Found!"));

        User user = tenant.getUser();

        if (user != null) {
            user.setTenant(null);
            userRepo.save(user);
        }
        TenantRepo.delete(tenant);
    }
}
