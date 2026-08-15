package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.BedRequest;
import com.codder.stayease.dto.BedResponse;
import com.codder.stayease.entity.Bed;
import com.codder.stayease.entity.Room;
import com.codder.stayease.repository.BedRepository;
import com.codder.stayease.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BedService {

    @Autowired
    private BedRepository bedRepo;

    @Autowired
    private RoomRepository roomRepo;


    // =========================
    // ADD BED
    // =========================

    public Bed addBed(BedRequest request) {

        Room room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Not Found!"
                        )
                );

        Bed bed = new Bed();

        bed.setBedNumber(request.getBedNumber());
        bed.setStatus(request.getStatus());
        bed.setRoom(room);

        return bedRepo.save(bed);
    }


    // =========================
    // GET ALL BEDS
    // =========================

    public List<BedResponse> getAllBed() {

        return bedRepo.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =========================
    // GET BED BY ID
    // =========================

    public BedResponse getById(long id) {

        Bed bed = bedRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bed Not Found!"
                        )
                );

        return convertToResponse(bed);
    }


    // =========================
    // UPDATE BED
    // =========================

    public BedResponse updateById(
            long id,
            BedRequest request
    ) {

        Bed bed = bedRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bed Not Found!"
                        )
                );

        Room room = roomRepo.findById(request.getRoomId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Not Found!"
                        )
                );

        bed.setBedNumber(request.getBedNumber());
        bed.setStatus(request.getStatus());
        bed.setRoom(room);

        Bed updatedBed = bedRepo.save(bed);

        return convertToResponse(updatedBed);
    }


    // =========================
    // DELETE BED
    // =========================

    public void deleteById(long id) {

        Bed bed = bedRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bed Not Found!"
                        )
                );

        bedRepo.delete(bed);
    }


    // =========================
    // ENTITY → RESPONSE DTO
    // =========================

    private BedResponse convertToResponse(Bed bed) {

        Room room = bed.getRoom();

        return new BedResponse(
                bed.getId(),
                bed.getBedNumber(),
                bed.getStatus(),

                room.getId(),
                room.getRoomNumber(),

                room.getFloor().getId(),
                room.getFloor().getFloorNumber(),

                room.getFloor().getBuilding().getId(),
                room.getFloor().getBuilding().getBuildingCode(),
                room.getFloor().getBuilding().getBuildingName()
        );
    }
}