package com.codder.stayease.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "visitor")
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String visitorName;

    private String visitorPhone;

    @Column(nullable = false)
    private LocalDate visitDate;

    private String purpose;

    private String entryTime;

    private String exitTime;

    /*
     * IMPORTANT:
     *
     * Do NOT use @JsonBackReference here.
     *
     * We want the tenant information to be returned
     * when /visitor/all is called.
     *
     * We ignore the Tenant's visitors collection so
     * Jackson does not create an infinite JSON loop.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id")
    @JsonIgnoreProperties({
            "visitors",
            "complaints"
    })
    private Tenant tenant;


    // =====================================================
    // CONSTRUCTORS
    // =====================================================

    public Visitor() {
    }


    public Visitor(
            String visitorName,
            String visitorPhone,
            LocalDate visitDate,
            String purpose,
            String entryTime,
            String exitTime) {

        this.visitorName = visitorName;
        this.visitorPhone = visitorPhone;
        this.visitDate = visitDate;
        this.purpose = purpose;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
    }


    // =====================================================
    // GETTERS & SETTERS
    // =====================================================

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