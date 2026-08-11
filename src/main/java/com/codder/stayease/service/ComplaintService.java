package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.ComplaintRequest;
import com.codder.stayease.entity.Complaint;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.repository.ComplaintRepository;
import com.codder.stayease.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository comRepo;

    @Autowired
    private TenantRepository tenantRepo;


    // ADD COMPLAINT
    public Complaint addComplaint(ComplaintRequest request) {

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant Not Found!"));

        Complaint complaint = new Complaint();

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setComplaintDate(request.getComplaintDate());
        complaint.setStatus(request.getStatus());
        complaint.setTenant(tenant);

        return comRepo.save(complaint);
    }


    // GET ALL COMPLAINTS
    public List<Complaint> getAllComplaint() {

        return comRepo.findAll();
    }


    // GET COMPLAINT BY ID
    public Complaint getComplaintById(long id) {

        return comRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint Not Found!"));
    }


    // UPDATE COMPLAINT
    public Complaint updateComplaintById(
            long id,
            ComplaintRequest request) {

        Complaint complaint = comRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint Not Found!"));

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant Not Found!"));

        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setComplaintDate(request.getComplaintDate());
        complaint.setStatus(request.getStatus());
        complaint.setTenant(tenant);

        return comRepo.save(complaint);
    }


    // DELETE COMPLAINT
    public void deleteComplaintById(long id) {

        Complaint complaint = comRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Complaint Not Found!"));

        comRepo.delete(complaint);
    }
}