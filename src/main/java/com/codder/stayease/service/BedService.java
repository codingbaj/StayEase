package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.BedRequest;
import com.codder.stayease.entity.Bed;
import com.codder.stayease.entity.Room;
import com.codder.stayease.entity.RoomType;
import com.codder.stayease.repository.BedRepository;
import com.codder.stayease.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedRepository BedRepo;

    @Autowired
    private RoomRepository RoomRepo;


    public Bed addBed(BedRequest request) {

        Room room = RoomRepo.findById(request.getRoomId())
                .orElseThrow(()->new ResourceNotFoundException("Room Not Found !"));

        Bed bed = new Bed();
        bed.setBedNumber(request.getBedNumber());
        bed.setRoom(room);
        bed.setStatus(request.getStatus());

        return BedRepo.save(bed);
    }

    public List<Bed> getAllBed() {
        return BedRepo.findAll();
    }

    public Bed getById(long id){
        Bed bed = BedRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Bed Not Found!"));

        return bed;
    }

    public Bed updateById(long id, BedRequest request) {

        Bed bed = BedRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Bed Not Found!"));

        Room room = RoomRepo.findById(request.getRoomId())
                .orElseThrow(()-> new ResourceNotFoundException("Room not Found!"));

        bed.setStatus(request.getStatus());
        bed.setRoom(room);
        bed.setBedNumber(request.getBedNumber());


        return BedRepo.save(bed);
    }

    public void deleteById(long id){
        Bed bed = BedRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Bed Not Found!"));

        BedRepo.delete(bed);

    }
}
