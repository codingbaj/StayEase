package com.codder.stayease.controller;

import com.codder.stayease.dto.FloorRequest;
import com.codder.stayease.entity.Floor;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/floor")
public class FloorController {

    @Autowired
    private FloorService service;

    @PostMapping("/add")
    public ApiResponse addFloor(@RequestBody FloorRequest request){
        Floor floor = service.addFloor(request);

        return new ApiResponse(
                true ,
                "Successfully Floor record added!",
                floor
        );
    }

    @GetMapping("/all")
    public ApiResponse getAllFloor(){
        List<Floor> floor = service.getAllFloor();

        return new ApiResponse(
                true ,
                "Successfully Fetched all floor!",
                floor
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable long id){
        Floor floor = service.getById(id);
        return new ApiResponse(
                true,
                "Floor successfully fetched!",
                floor
        );
    }

    @PutMapping("update/{id}")
    public ApiResponse updateFloor(@PathVariable long id , @RequestBody FloorRequest request){
        Floor floor = service.updateFloor(id,request);

        return new ApiResponse(
                true ,
                "Floor Successfully Updated!",
                floor
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteFloor(@PathVariable long id){
        service.deleteFloor(id);

        return new ApiResponse(
                true,
                "Floor Successfully Deleted!",
                null
        );
    }
}
