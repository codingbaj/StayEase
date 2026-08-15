package com.codder.stayease.dto;

import java.time.LocalDate;

public class AllocationResponse {

    private long id;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private String status;

    // Tenant information
    private long tenantId;
    private String tenantName;
    private String tenantEmail;

    // Bed information
    private long bedId;
    private String bedNumber;

    // Room information
    private long roomId;
    private String roomNumber;

    // Floor information
    private long floorId;
    private int floorNumber;

    // Building information
    private long buildingId;
    private String buildingCode;
    private String buildingName;


    public AllocationResponse() {
    }


    public AllocationResponse(
            long id,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            String status,
            long tenantId,
            String tenantName,
            String tenantEmail,
            long bedId,
            String bedNumber,
            long roomId,
            String roomNumber,
            long floorId,
            int floorNumber,
            long buildingId,
            String buildingCode,
            String buildingName
    ) {

        this.id = id;

        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;

        this.tenantId = tenantId;
        this.tenantName = tenantName;
        this.tenantEmail = tenantEmail;

        this.bedId = bedId;
        this.bedNumber = bedNumber;

        this.roomId = roomId;
        this.roomNumber = roomNumber;

        this.floorId = floorId;
        this.floorNumber = floorNumber;

        this.buildingId = buildingId;
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public long getBedId() {
        return bedId;
    }

    public void setBedId(long bedId) {
        this.bedId = bedId;
    }


    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }


    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }


    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }


    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }


    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }


    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }


    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
}