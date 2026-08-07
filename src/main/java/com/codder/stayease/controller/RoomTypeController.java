package com.codder.stayease.controller;


import com.codder.stayease.entity.RoomType;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roomtype")
public class RoomTypeController {

    @Autowired
    private RoomTypeService service;

    @PostMapping("/add")
    public ApiResponse addRoomType(@RequestBody RoomType rt){
        RoomType room = service.addRoomType(rt);
        return new ApiResponse(
                true ,
                "RoomType Successfully Added!",
                room
        );
    }

    @GetMapping("/all")
    public ApiResponse getAllRooms(){
        List<RoomType> r  = service.getAllRooms();
        return new ApiResponse(
                true,
                "All RoomType Fetched!",
                r
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getByid(@PathVariable long id){
        RoomType room = service.getByid(id);
        return new ApiResponse(
                true ,
                "RoomType Successfuly Fetched!",
                room
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateByid(@PathVariable long id , @RequestBody RoomType r){
        RoomType room = service.updateByid(id,r);
        return new ApiResponse(
                true ,
                "RoomTyper Successfully Updated!",
                room
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteByid(@PathVariable long id){
        service.deleteByid(id);

        return new ApiResponse(
                true ,
                "RoomType Successfully Removed!",
                null
        );
    }
}
