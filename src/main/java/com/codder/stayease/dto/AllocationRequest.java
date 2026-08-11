package com.codder.stayease.dto;

import java.time.LocalDate;

public class AllocationRequest {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
    private long tenantId;
    private long bedId;

    public AllocationRequest(LocalDate checkInDate, LocalDate checkOutDate, String status, long tenantId, long bedId) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
        this.tenantId = tenantId;
        this.bedId = bedId;
    }

    public AllocationRequest() {
    }


    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
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

    public long getBedId() {
        return bedId;
    }

    public void setBedId(long bedId) {
        this.bedId = bedId;
    }
}
