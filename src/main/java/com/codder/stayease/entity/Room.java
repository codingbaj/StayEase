package com.codder.stayease.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomNumber;

    private String status;

    @JsonBackReference("floor-room")
    @ManyToOne
    @JoinColumn(name = "floor_id")
    private Floor floor;

    @JsonBackReference("roomtype-room")
    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @JsonManagedReference("room-bed")
    @OneToMany(mappedBy = "room")
    private List<Bed> beds;

    public Room(String roomNumber, String status) {
        this.roomNumber = roomNumber;
        this.status = status;
    }

    public Room() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }
}