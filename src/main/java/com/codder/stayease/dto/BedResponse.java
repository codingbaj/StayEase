
package com.codder.stayease.dto;

public class BedResponse {

    private long id;
    private String bedNumber;
    private String status;

    private long roomId;
    private String roomNumber;

    private long floorId;
    private int floorNumber;

    private long buildingId;
    private String buildingCode;
    private String buildingName;

    public BedResponse() {
    }

    public BedResponse(
            long id,
            String bedNumber,
            String status,
            long roomId,
            String roomNumber,
            long floorId,
            int floorNumber,
            long buildingId,
            String buildingCode,
            String buildingName
    ) {
        this.id = id;
        this.bedNumber = bedNumber;
        this.status = status;
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