package com.codder.stayease.dto;

import java.time.LocalDate;

public class RentResponse {

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

    // Tenant information
    private long tenantId;
    private String tenantName;
    private String tenantEmail;

    public RentResponse() {
    }

    public RentResponse(
            long id,
            int month,
            int year,
            LocalDate dueDate,
            double roomRent,
            double electricityBill,
            double waterBill,
            double maintenanceCharge,
            double lateFine,
            double totalAmount,
            String status,
            long tenantId,
            String tenantName,
            String tenantEmail
    ) {
        this.id = id;
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
        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.tenantEmail = tenantEmail;
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

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }
}