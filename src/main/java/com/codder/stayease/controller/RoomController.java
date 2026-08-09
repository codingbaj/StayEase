package com.codder.stayease.controller;

import com.codder.stayease.dto.RoomRequest;
import com.codder.stayease.entity.Room;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping("/add")
    public ApiResponse addRoom(@RequestBody RoomRequest request){
        Room room = service.addRoom(request);

        return new ApiResponse(
                true ,
                "Room Successfully Added!",
                room
        );
    }

    @GetMapping("/all")
    public ApiResponse allRoom(){
        List<Room> rooms = service.allRoom();
        return new ApiResponse(
                true ,
                "All Room Fetched!",
                rooms
        );
    }

    @GetMapping("/{id}")
    public  ApiResponse getById(@PathVariable long id){
        Room room = service.getById(id);
        return new ApiResponse(
                true ,
                "Room Successfully Fetched!",
                room
        );
    }

    @PutMapping("update/{id}")
    public ApiResponse updateByid(@PathVariable long id , @RequestBody RoomRequest request){
        Room room = service.updateByid(id,request);
        return new ApiResponse(
                true ,
                "Successfully Update Room !",
                room
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteById(@PathVariable long id){
        service.deleteById(id);
        return new ApiResponse(
                true ,
                "Room Successfully Removed!",
                null
        );
    }


}
