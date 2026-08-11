package com.codder.stayease.dto;

import java.time.LocalDate;

public class ComplaintRequest {

    private String title;
    private String description;
    private LocalDate complaintDate;
    private String status;
    private long tenantId;

    public ComplaintRequest(String title,
                            String description,
                            LocalDate complaintDate,
                            String status,
                            long tenantId) {

        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
        this.tenantId = tenantId;
    }

    public ComplaintRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }
}