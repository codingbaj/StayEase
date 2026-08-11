package com.codder.stayease.dto;

public class TenantRequest {

    private String guardianName;
    private String guardianPhone;
    private String address;
    private String aadhaarNo;
    private String occupation;
    private long userId;

    public TenantRequest(String guardianName, String guardianPhone, String address, String aadhaarNo, String occupation, long userId) {
        this.guardianName = guardianName;
        this.guardianPhone = guardianPhone;
        this.address = address;
        this.aadhaarNo = aadhaarNo;
        this.occupation = occupation;
        this.userId = userId;
    }

    public TenantRequest() {
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadhaarNo() {
        return aadhaarNo;
    }

    public void setAadhaarNo(String aadhaarNo) {
        this.aadhaarNo = aadhaarNo;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}

