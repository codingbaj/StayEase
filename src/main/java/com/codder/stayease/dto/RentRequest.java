package com.codder.stayease.dto;

import java.time.LocalDate;

public class RentRequest {

    private int month;
    private int year;
    private LocalDate dueDate;

    private double roomRent;
    private double electricityBill;
    private double waterBill;
    private double maintenanceCharge;

    private String status;

    private long tenantId;


    public RentRequest() {
    }


    public RentRequest(int month,
                       int year,
                       LocalDate dueDate,
                       double roomRent,
                       double electricityBill,
                       double waterBill,
                       double maintenanceCharge,
                       String status,
                       long tenantId) {

        this.month = month;
        this.year = year;
        this.dueDate = dueDate;
        this.roomRent = roomRent;
        this.electricityBill = electricityBill;
        this.waterBill = waterBill;
        this.maintenanceCharge = maintenanceCharge;
        this.status = status;
        this.tenantId = tenantId;
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