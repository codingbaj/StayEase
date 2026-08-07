package com.codder.stayease.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomType;

    private int capacity;

    private double rent;

    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms;

    public RoomType(String roomType, int capacity, double rent) {
        this.roomType = roomType;
        this.capacity = capacity;
        this.rent = rent;
    }

    public RoomType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}