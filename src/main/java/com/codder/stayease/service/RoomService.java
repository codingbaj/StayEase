package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.RoomRequest;
import com.codder.stayease.entity.Floor;
import com.codder.stayease.entity.Room;
import com.codder.stayease.entity.RoomType;
import com.codder.stayease.repository.FloorRepository;
import com.codder.stayease.repository.RoomRepository;
import com.codder.stayease.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository RoomRepo;

    @Autowired
    private RoomTypeRepository RoomTypeRepo;

    @Autowired
    private FloorRepository FloorRepo;


    public Room addRoom(RoomRequest request) {

        RoomType roomType = RoomTypeRepo.findById(request.getRoomTypeId()).
                orElseThrow(()-> new ResourceNotFoundException("RoomType not found!"));

        Floor floor = FloorRepo.findById(request.getFloorId()). orElseThrow(()-> new ResourceNotFoundException("Floor Not Found!"));

        Room room = new Room();
        room.setFloor(floor);
        room.setRoomType(roomType);
        room.setRoomNumber(request.getRoomNumber());
        room.setStatus(request.getStatus());

        return RoomRepo.save(room);

    }

    public List<Room> allRoom() {

        return RoomRepo.findAll();
    }

    public Room getById(long id) {

        Room room = RoomRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Room Not Found !"));

        return room;
    }

    public Room updateByid(long id, RoomRequest request) {

        Room room = RoomRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Room Not Found!"));

        RoomType roomType = RoomTypeRepo.findById(request.getRoomTypeId()).orElseThrow(()-> new ResourceNotFoundException("RoomType Not Found!"));

        Floor floor = FloorRepo.findById(request.getFloorId()).orElseThrow(()->new ResourceNotFoundException("Floor Not Found!"));

        room.setStatus(request.getStatus());
        room.setRoomNumber(request.getRoomNumber());
        room.setRoomType(roomType);
        room.setFloor(floor);
        return RoomRepo.save(room);
    }

    public void deleteById(long id) {

        Room room = RoomRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room Not Found !"));

        RoomRepo.delete(room);
    }
}
