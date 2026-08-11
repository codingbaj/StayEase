package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.VisitorRequest;
import com.codder.stayease.entity.Tenant;
import com.codder.stayease.entity.Visitor;
import com.codder.stayease.repository.TenantRepository;
import com.codder.stayease.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    @Autowired private VisitorRepository visitorRepo;
    @Autowired private TenantRepository tenantRepo;

    public Visitor addVisitor(VisitorRequest request) {
        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant Not Found!"));
        return saveVisitor(new Visitor(), request, tenant);
    }

    public Visitor addVisitorForTenant(VisitorRequest request, long userId) {
        Tenant tenant = tenantRepo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant profile not found!"));
        return saveVisitor(new Visitor(), request, tenant);
    }

    private Visitor saveVisitor(Visitor visitor, VisitorRequest request, Tenant tenant) {
        visitor.setVisitorName(request.getVisitorName());
        visitor.setVisitorPhone(request.getVisitorPhone());
        visitor.setVisitDate(request.getVisitDate());
        visitor.setPurpose(request.getPurpose());
        visitor.setEntryTime(request.getEntryTime());
        visitor.setExitTime(request.getExitTime());
        visitor.setTenant(tenant);
        return visitorRepo.save(visitor);
    }

    public List<Visitor> getAllVisitor() { return visitorRepo.findAll(); }

    public Visitor getVisitorById(long id) {
        return visitorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Visitor Not Found!"));
    }

    public List<Visitor> getMyVisitors(long userId) { return visitorRepo.findByTenant_User_Id(userId); }

    public Visitor getMyVisitorById(long id, long userId) {
        return visitorRepo.findByIdAndTenant_User_Id(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor Not Found for this Tenant!"));
    }

    public Visitor updateVisitorById(long id, VisitorRequest request) {
        Visitor visitor = getVisitorById(id);
        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("Tenant Not Found!"));
        return saveVisitor(visitor, request, tenant);
    }

    public Visitor updateMyVisitor(long id, VisitorRequest request, long userId) {
        Visitor visitor = getMyVisitorById(id, userId);
        Tenant tenant = visitor.getTenant();
        return saveVisitor(visitor, request, tenant);
    }

    public void deleteVisitorById(long id) {
        visitorRepo.delete(getVisitorById(id));
    }

    public void deleteMyVisitor(long id, long userId) {
        visitorRepo.delete(getMyVisitorById(id, userId));
    }
}
