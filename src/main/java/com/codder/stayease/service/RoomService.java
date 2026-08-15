package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.RoomRequest;
import com.codder.stayease.dto.RoomResponse;
import com.codder.stayease.entity.Floor;
import com.codder.stayease.entity.Room;
import com.codder.stayease.entity.RoomType;
import com.codder.stayease.repository.FloorRepository;
import com.codder.stayease.repository.RoomRepository;
import com.codder.stayease.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private RoomTypeRepository roomTypeRepo;

    @Autowired
    private FloorRepository floorRepo;


    // =====================================================
    // ADD ROOM
    // =====================================================

    @Transactional
    public Room addRoom(RoomRequest request) {

        RoomType roomType = roomTypeRepo.findById(request.getRoomTypeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "RoomType not found!"
                        )
                );

        Floor floor = floorRepo.findById(request.getFloorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor not found!"
                        )
                );

        Room room = new Room();

        room.setRoomNumber(request.getRoomNumber());
        room.setStatus(request.getStatus());

        room.setFloor(floor);
        room.setRoomType(roomType);

        return roomRepo.save(room);
    }


    // =====================================================
    // GET ALL ROOMS
    // =====================================================

    @Transactional(readOnly = true)
    public List<RoomResponse> allRoom() {

        List<Room> rooms = roomRepo.findAll();

        return rooms.stream()
                .map(this::convertToResponse)
                .toList();
    }


    // =====================================================
    // GET ROOM BY ID
    // =====================================================

    @Transactional(readOnly = true)
    public RoomResponse getById(long id) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Not Found!"
                        )
                );

        return convertToResponse(room);
    }


    // =====================================================
    // UPDATE ROOM
    // =====================================================

    @Transactional
    public Room updateById(long id, RoomRequest request) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Not Found!"
                        )
                );

        RoomType roomType = roomTypeRepo.findById(
                        request.getRoomTypeId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "RoomType Not Found!"
                        )
                );

        Floor floor = floorRepo.findById(
                        request.getFloorId()
                )
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Floor Not Found!"
                        )
                );


        room.setRoomNumber(
                request.getRoomNumber()
        );

        room.setStatus(
                request.getStatus()
        );

        room.setFloor(floor);

        room.setRoomType(roomType);


        return roomRepo.save(room);
    }


    // =====================================================
    // DELETE ROOM
    // =====================================================

    @Transactional
    public void deleteById(long id) {

        Room room = roomRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Room Not Found!"
                        )
                );

        roomRepo.delete(room);
    }


    // =====================================================
    // CONVERT ROOM ENTITY → ROOM RESPONSE
    // =====================================================

    private RoomResponse convertToResponse(Room room) {

        RoomResponse response = new RoomResponse();


        // =================================================
        // ROOM INFORMATION
        // =================================================

        response.setId(
                room.getId()
        );

        response.setRoomNumber(
                room.getRoomNumber()
        );

        response.setStatus(
                room.getStatus()
        );


        // =================================================
        // FLOOR INFORMATION
        // =================================================

        if (room.getFloor() != null) {

            Floor floor = room.getFloor();

            response.setFloorId(
                    floor.getId()
            );

            response.setFloorNumber(
                    floor.getFloorNumber()
            );


            // =============================================
            // BUILDING INFORMATION
            // =============================================

            if (floor.getBuilding() != null) {

                response.setBuildingId(
                        floor.getBuilding().getId()
                );

                response.setBuildingCode(
                        floor.getBuilding().getBuildingCode()
                );

                response.setBuildingName(
                        floor.getBuilding().getBuildingName()
                );
            }
        }


        // =================================================
        // ROOM TYPE INFORMATION
        // =================================================

        if (room.getRoomType() != null) {

            RoomType roomType = room.getRoomType();

            response.setRoomTypeId(
                    roomType.getId()
            );

            response.setRoomType(
                    roomType.getRoomType()
            );

            response.setCapacity(
                    roomType.getCapacity()
            );

            response.setRent(
                    roomType.getRent()
            );
        }


        return response;
    }
}