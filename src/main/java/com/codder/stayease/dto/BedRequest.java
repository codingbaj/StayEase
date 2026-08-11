package com.codder.stayease.dto;

public class BedRequest {

    private String bedNumber;
    private String status;
    private long roomId;

    public BedRequest(String bedNumber, String status, long roomId) {
        this.bedNumber = bedNumber;
        this.status = status;
        this.roomId = roomId;
    }

    public BedRequest() {
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
