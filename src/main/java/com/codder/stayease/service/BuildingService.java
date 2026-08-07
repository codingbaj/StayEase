package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.entity.Building;
import com.codder.stayease.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    @Autowired
   private BuildingRepository repo;
    public Building addBuilding(Building building) {

        return repo.save(building);

    }

    public List<Building> allBuilding() {
        return repo.findAll();
    }

    public Building getBuildingById(long id) {
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Building not Found !"));
    }

    public Building updateBuilding(long id, Building building) {

        Building b = repo.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Building not found!"));

        b.setAddress(building.getAddress());
        b.setBuildingCode(building.getBuildingCode());
        b.setBuildingName(building.getBuildingName());
        b.setDescription(building.getDescription());
        b.setTotalFloors(building.getTotalFloors());
        return repo.save(b);
    }

    public void deleteBuilding(long id) {

        Building b = repo.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Building not Found !") );
        repo.delete(b);
    }
}
