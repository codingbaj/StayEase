package com.codder.stayease.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate complaintDate;

    @Column(nullable = false)
    private String status;

    /*
     * IMPORTANT
     *
     * Do NOT use @JsonBackReference here.
     *
     * We want the tenant to be included in the
     * Complaint JSON response so the frontend can display:
     *
     * row.tenant.user.name
     *
     * Ignore the Tenant's complaints collection to prevent
     * infinite JSON recursion.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tenant_id", nullable = false)
    @JsonIgnoreProperties({
            "complaints"
    })
    private Tenant tenant;


    // =====================================================
    // CONSTRUCTORS
    // =====================================================

    public Complaint() {
    }

    public Complaint(
            String title,
            String description,
            LocalDate complaintDate,
            String status) {

        this.title = title;
        this.description = description;
        this.complaintDate = complaintDate;
        this.status = status;
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


    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}