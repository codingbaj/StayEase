package com.codder.stayease.controller;

import com.codder.stayease.dto.RoomRequest;
import com.codder.stayease.dto.RoomResponse;
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


    // =====================================================
    // ADD ROOM
    // =====================================================

    @PostMapping("/add")
    public ApiResponse addRoom(
            @RequestBody RoomRequest request) {

        Room room = service.addRoom(request);

        return new ApiResponse(
                true,
                "Room Successfully Added!",
                room
        );
    }


    // =====================================================
    // GET ALL ROOMS
    // =====================================================

    @GetMapping("/all")
    public ApiResponse allRoom() {

        List<RoomResponse> rooms = service.allRoom();

        return new ApiResponse(
                true,
                "All Room Fetched!",
                rooms
        );
    }


    // =====================================================
    // GET ROOM BY ID
    // =====================================================

    @GetMapping("/{id}")
    public ApiResponse getById(
            @PathVariable long id) {

        RoomResponse room = service.getById(id);

        return new ApiResponse(
                true,
                "Room Successfully Fetched!",
                room
        );
    }


    // =====================================================
    // UPDATE ROOM
    // =====================================================

    @PutMapping("/update/{id}")
    public ApiResponse updateById(
            @PathVariable long id,
            @RequestBody RoomRequest request) {

        Room room = service.updateById(
                id,
                request
        );

        return new ApiResponse(
                true,
                "Successfully Updated Room!",
                room
        );
    }


    // =====================================================
    // DELETE ROOM
    // =====================================================

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteById(
            @PathVariable long id) {

        service.deleteById(id);

        return new ApiResponse(
                true,
                "Room Successfully Removed!",
                null
        );
    }
}