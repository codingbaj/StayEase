package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.FloorRequest;
import com.codder.stayease.entity.Building;
import com.codder.stayease.entity.Floor;
import com.codder.stayease.repository.BuildingRepository;
import com.codder.stayease.repository.FloorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorService {

    @Autowired
    private FloorRepository Floorepo;

   @Autowired
    private BuildingRepository Buildingrepo;

    public Floor addFloor(FloorRequest request) {

        Building building = Buildingrepo.findById(request.getBuildingId()).orElseThrow(()->
                new ResourceNotFoundException("Building not found!"));


        Floor floor = new Floor();

        floor.setFloorNumber(request.getFloorNumber());

        floor.setBuilding(building);

        return Floorepo.save(floor);
    }

    public List<Floor> getAllFloor() {
        return Floorepo.findAll();
    }

    public Floor getById(long id) {

        return Floorepo.findById(id).
                orElseThrow(()-> new ResourceNotFoundException("Floor not Found!"));
    }

    public Floor updateFloor(long id , FloorRequest request) {

        Floor floor = Floorepo.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Floor not Found!"));

        Building building = Buildingrepo.findById(request.getBuildingId())
                .orElseThrow(()-> new ResourceNotFoundException("Building not Found!"));

        floor.setFloorNumber(request.getFloorNumber());
        floor.setBuilding(building);

        return Floorepo.save(floor);
    }

    public void deleteFloor(long id) {

        Floor floor = Floorepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Floor not Found!"));

        Floorepo.delete(floor);
    }
}
