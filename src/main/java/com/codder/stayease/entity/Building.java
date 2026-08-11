package com.codder.stayease.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "building_code", unique = true)
    private String buildingCode;

    @Column(name = "building_name")
    private String buildingName;

    private String address;

    @Column(name = "total_floors")
    private int totalFloors;

    private String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "building")
    private List<Floor> floors;

    public Building(String buildingCode, String buildingName,
                    String address, int totalFloors,
                    String description) {
        this.buildingCode = buildingCode;
        this.buildingName = buildingName;
        this.address = address;
        this.totalFloors = totalFloors;
        this.description = description;
    }

    public Building() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }
}