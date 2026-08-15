package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.FloorRequest;
import com.codder.stayease.dto.FloorResponse;
import com.codder.stayease.entity.Building;
import com.codder.stayease.entity.Floor;
import com.codder.stayease.repository.BuildingRepository;
import com.codder.stayease.repository.FloorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FloorService {

    @Autowired
    private FloorRepository floorRepo;

    @Autowired
    private BuildingRepository buildingRepo;


    // =========================
    // ADD FLOOR
    // =========================

    @Transactional
    public FloorResponse addFloor(FloorRequest request) {

        Building building = buildingRepo.findById(request.getBuildingId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Building not found!"
                        )
                );

        Floor floor = new Floor();

        floor.setFloorNumber(request.getFloorNumber());
        floor.setBuilding(building);

        Floor savedFloor = floorRepo.save(floor);

        return FloorResponse.fromEntity(savedFloor);
    }


    // =========================
    // GET ALL FLOORS
    // =========================

    @Transactional(readOnly = true)
    public List<FloorResponse> getAllFloor() {

        return floorRepo.findAll()
                .stream()
                .map(FloorResponse::fromEntity)
                .toList();
    }


    // =========================
    // GET FLOOR BY ID
    // =========================

    @Transactional(readOnly = true)
    public FloorResponse getById(long id) {

        Floor floor = floorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor not Found!"
                        )
                );

        return FloorResponse.fromEntity(floor);
    }


    // =========================
    // UPDATE FLOOR
    // =========================

    @Transactional
    public FloorResponse updateFloor(
            long id,
            FloorRequest request) {

        Floor floor = floorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor not Found!"
                        )
                );

        Building building = buildingRepo.findById(
                request.getBuildingId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Building not Found!"
                )
        );

        floor.setFloorNumber(request.getFloorNumber());
        floor.setBuilding(building);

        Floor updatedFloor = floorRepo.save(floor);

        return FloorResponse.fromEntity(updatedFloor);
    }


    // =========================
    // DELETE FLOOR
    // =========================

    @Transactional
    public void deleteFloor(long id) {

        Floor floor = floorRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor not Found!"
                        )
                );

        floorRepo.delete(floor);
    }
}