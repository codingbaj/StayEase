package com.codder.stayease.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int month;

    private int year;

    private LocalDate dueDate;

    private double roomRent;

    private double electricityBill;

    private double waterBill;

    private double maintenanceCharge;

    private double lateFine;

    private double totalAmount;

    private String status;

    @JsonBackReference("tenant-rent")
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @OneToOne(mappedBy = "rent")
    private Payment payment;


    public Rent(int month,
                int year,
                LocalDate dueDate,
                double roomRent,
                double electricityBill,
                double waterBill,
                double maintenanceCharge,
                double lateFine,
                double totalAmount,
                String status) {

        this.month = month;
        this.year = year;
        this.dueDate = dueDate;
        this.roomRent = roomRent;
        this.electricityBill = electricityBill;
        this.waterBill = waterBill;
        this.maintenanceCharge = maintenanceCharge;
        this.lateFine = lateFine;
        this.totalAmount = totalAmount;
        this.status = status;
    }


    public Rent() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public double getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(double roomRent) {
        this.roomRent = roomRent;
    }

    public double getElectricityBill() {
        return electricityBill;
    }

    public void setElectricityBill(double electricityBill) {
        this.electricityBill = electricityBill;
    }

    public double getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(double waterBill) {
        this.waterBill = waterBill;
    }

    public double getMaintenanceCharge() {
        return maintenanceCharge;
    }

    public void setMaintenanceCharge(double maintenanceCharge) {
        this.maintenanceCharge = maintenanceCharge;
    }

    public double getLateFine() {
        return lateFine;
    }

    public void setLateFine(double lateFine) {
        this.lateFine = lateFine;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}