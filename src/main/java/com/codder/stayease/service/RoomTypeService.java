package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.entity.RoomType;
import com.codder.stayease.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeService {

    @Autowired
    private RoomTypeRepository repo;

    public RoomType addRoomType(RoomType rt) {
        return  repo.save(rt);
    }

    public List<RoomType> getAllRooms() {
        return repo.findAll();
    }

    public RoomType getByid(long id) {

        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("RoomType Not Found !"));

    }

    public RoomType updateByid(long id, RoomType r) {
        RoomType room = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("RoomType not Found !")) ;
        room.setCapacity(r.getCapacity());
        room.setRent(r.getRent());
        room.setRoomType(r.getRoomType());
        //room.setRooms(r.getRooms());

        return repo.save(room);
    }

    public void deleteByid(long id) {

       RoomType room =  repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("RoomType not Found !"));

       repo.delete(room);
    }
}
