package com.codder.stayease.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String visitorName;

    private String visitorPhone;

    private LocalDate visitDate;

    private String purpose;

    private String entryTime;

    private String exitTime;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public Visitor(String visitorName, String visitorPhone,
                   LocalDate visitDate, String purpose,
                   String entryTime, String exitTime) {

        this.visitorName = visitorName;
        this.visitorPhone = visitorPhone;
        this.visitDate = visitDate;
        this.purpose = purpose;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }

    public Visitor() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
