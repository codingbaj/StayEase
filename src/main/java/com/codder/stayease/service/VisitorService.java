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

    @Autowired
    private VisitorRepository visitorRepo;

    @Autowired
    private TenantRepository tenantRepo;


    // ADD VISITOR
    public Visitor addVisitor(VisitorRequest request) {

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant Not Found!"));

        Visitor visitor = new Visitor();

        visitor.setVisitorName(request.getVisitorName());
        visitor.setVisitorPhone(request.getVisitorPhone());
        visitor.setVisitDate(request.getVisitDate());
        visitor.setPurpose(request.getPurpose());
        visitor.setEntryTime(request.getEntryTime());
        visitor.setExitTime(request.getExitTime());
        visitor.setTenant(tenant);

        return visitorRepo.save(visitor);
    }


    // GET ALL VISITORS
    public List<Visitor> getAllVisitor() {

        return visitorRepo.findAll();
    }


    // GET VISITOR BY ID
    public Visitor getVisitorById(long id) {

        return visitorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Visitor Not Found!"));
    }


    // UPDATE VISITOR
    public Visitor updateVisitorById(
            long id,
            VisitorRequest request) {

        Visitor visitor = visitorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Visitor Not Found!"));

        Tenant tenant = tenantRepo.findById(request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant Not Found!"));

        visitor.setVisitorName(request.getVisitorName());
        visitor.setVisitorPhone(request.getVisitorPhone());
        visitor.setVisitDate(request.getVisitDate());
        visitor.setPurpose(request.getPurpose());
        visitor.setEntryTime(request.getEntryTime());
        visitor.setExitTime(request.getExitTime());
        visitor.setTenant(tenant);

        return visitorRepo.save(visitor);
    }


    // DELETE VISITOR
    public void deleteVisitorById(long id) {

        Visitor visitor = visitorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Visitor Not Found!"));

        visitorRepo.delete(visitor);
    }
}