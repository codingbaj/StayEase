package com.codder.stayease.controller;

import com.codder.stayease.dto.BedRequest;
import com.codder.stayease.entity.Bed;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.BedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bed")
public class BedController {

    @Autowired
    private BedService service;

    @PostMapping("/add")
    public ApiResponse addBed(@RequestBody BedRequest request){
        Bed bed = service.addBed(request);

        return new ApiResponse(
                true,
                "Bed Successfully Added!",
                bed
        );
    }

    @GetMapping("/all")
    public ApiResponse getAllBed(){
        List<Bed> bed = service.getAllBed();
        return new ApiResponse(
                true ,
                "Successfully all beds Fetched!",
                bed
        );
    }

    @GetMapping("/{id}")
    public ApiResponse getbyId(@PathVariable long id){
        Bed bed = service.getById(id);
        return new ApiResponse(
                true ,
                "Successfully all beds Fetched!",
                bed
        );
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateById(@PathVariable long id , @RequestBody BedRequest request){
        Bed bed  = service.updateById(id,request);
        return new ApiResponse(
                true,
                "Bed Successfully Updated!",
                bed
        );
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteById(@PathVariable long id){
        service.deleteById(id);

        return new ApiResponse(
                true,
                "Bed Successfully Deleted!",
                null
        );
    }


}
