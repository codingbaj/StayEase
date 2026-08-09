package com.codder.stayease.dto;

public class RoomRequest {

    private String roomNumber;

    private String status;

    private long floorId;

    private long roomTypeId;

    public RoomRequest() {
    }

    public RoomRequest(String roomNumber, String status, long floorId, long roomTypeId) {
        this.roomNumber = roomNumber;
        this.status = status;
        this.floorId = floorId;
        this.roomTypeId = roomTypeId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getFloorId() {
        return floorId;
    }

    public void setFloorId(long floorId) {
        this.floorId = floorId;
    }

    public long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}