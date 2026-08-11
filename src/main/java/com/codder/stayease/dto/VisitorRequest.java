package com.codder.stayease.dto;

import java.time.LocalDate;

public class VisitorRequest {

    private String visitorName;

    private String visitorPhone;

    private LocalDate visitDate;

    private String purpose;

    private String entryTime;

    private String exitTime;

    private long tenantId;


    public VisitorRequest(String visitorName,
                          String visitorPhone,
                          LocalDate visitDate,
                          String purpose,
                          String entryTime,
                          String exitTime,
                          long tenantId) {

        this.visitorName = visitorName;
        this.visitorPhone = visitorPhone;
        this.visitDate = visitDate;
        this.purpose = purpose;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.tenantId = tenantId;
    }


    public VisitorRequest() {
    }


    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }


    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }


    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }


    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }


    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }


    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }
}