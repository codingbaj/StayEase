package com.codder.stayease.dto;

import com.codder.stayease.entity.Floor;

public class FloorResponse {

    private long id;
    private int floorNumber;

    private long buildingId;
    private String buildingCode;
    private String buildingName;

    public FloorResponse() {
    }

    public FloorResponse(
            long id,
            int floorNumber,
            long buildingId,
            String buildingCode,
            String buildingName) {

        this.id = id;
        this.floorNumber = floorNumber;
        this.buildingId = buildingId;
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
    }

    public static FloorResponse fromEntity(Floor floor) {

        if (floor == null) {
            return null;
        }

        if (floor.getBuilding() == null) {

            return new FloorResponse(
                    floor.getId(),
                    floor.getFloorNumber(),
                    0,
                    null,
                    null
            );
        }

        return new FloorResponse(
                floor.getId(),
                floor.getFloorNumber(),
                floor.getBuilding().getId(),
                floor.getBuilding().getBuildingCode(),
                floor.getBuilding().getBuildingName()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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