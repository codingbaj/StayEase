package com.codder.stayease.controller;


import com.codder.stayease.entity.Building;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
   private BuildingService service;
    @PostMapping("/add")
    public ApiResponse addBuilding(@RequestBody Building building){

        Building savedBuilding = service.addBuilding(building);
        return new ApiResponse(
                true,
                "Building Successfully Added",
                savedBuilding
        );
    }

    @GetMapping("/all")
    public ApiResponse allBuilding(){
        List<Building> b = service.allBuilding();
        return new ApiResponse(
                true,
                "Successfully return all the Buildings .",
                b
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getBuildingById(@PathVariable long id){
        Building b = service.getBuildingById(id);
        return new ApiResponse(
                true,
                "Successfully find the Building!",
                b
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateBuilding(@PathVariable long id , @RequestBody Building building){
        Building b = service.updateBuilding(id , building);
        return new ApiResponse(
                true ,
                "Successfully Update the Building details.",
                b
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteBuilding(@PathVariable long id){
        service.deleteBuilding(id);
        return new ApiResponse(
                true ,
                "Successfully Delete Building Details!",
                null
        );
    }
}
