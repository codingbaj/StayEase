package com.codder.stayease.dto;

public class FloorRequest {

    private int floorNumber;
    private long buildingId;

    public FloorRequest() {
    }

    public FloorRequest(int floorNumber, long buildingId) {
        this.floorNumber = floorNumber;
        this.buildingId = buildingId;
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
}
