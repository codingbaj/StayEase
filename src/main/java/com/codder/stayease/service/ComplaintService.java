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

    public Complaint addComplaint(ComplaintRequest request) {
        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant Not Found!"));
        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setComplaintDate(request.getComplaintDate());
        complaint.setStatus(request.getStatus());
        complaint.setTenant(tenant);
        return comRepo.save(complaint);
    }

    public Complaint addComplaintForTenant(ComplaintRequest request, long userId) {
        Tenant tenant = tenantRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant profile not found!"));
        Complaint complaint = new Complaint();
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setComplaintDate(request.getComplaintDate());
        complaint.setStatus("PENDING");
        complaint.setTenant(tenant);
        return comRepo.save(complaint);
    }

    public List<Complaint> getAllComplaint() { return comRepo.findAll(); }

    public Complaint getComplaintById(long id) {
        return comRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Complaint Not Found!"));
    }

    public List<Complaint> getMyComplaints(long userId) {
        return comRepo.findByTenant_User_Id(userId);
    }

    public Complaint getMyComplaintById(long id, long userId) {
        return comRepo.findByIdAndTenant_User_Id(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint Not Found for this Tenant!"));
    }

    public Complaint updateComplaintById(long id, ComplaintRequest request) {
        Complaint complaint = comRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint Not Found!"));
        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant Not Found!"));
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setComplaintDate(request.getComplaintDate());
        complaint.setStatus(request.getStatus());
        complaint.setTenant(tenant);
        return comRepo.save(complaint);
    }

    public Complaint updateMyComplaint(long id, ComplaintRequest request, long userId) {
        Complaint complaint = getMyComplaintById(id, userId);
        complaint.setTitle(request.getTitle());
        complaint.setDescription(request.getDescription());
        complaint.setComplaintDate(request.getComplaintDate());
        // Tenant cannot change the workflow status or tenant ownership.
        return comRepo.save(complaint);
    }

    public void deleteComplaintById(long id) {
        Complaint complaint = comRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Complaint Not Found!"));
        comRepo.delete(complaint);
    }

    public void deleteMyComplaint(long id, long userId) {
        Complaint complaint = getMyComplaintById(id, userId);
        comRepo.delete(complaint);
    }
}
