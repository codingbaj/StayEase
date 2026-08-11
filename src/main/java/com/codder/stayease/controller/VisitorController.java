package com.codder.stayease.controller;

import com.codder.stayease.dto.VisitorRequest;
import com.codder.stayease.entity.Visitor;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Autowired
    private VisitorService service;


    // ADD VISITOR
    @PostMapping("/add")
    public ApiResponse addVisitor(
            @RequestBody VisitorRequest request) {

        Visitor visitor = service.addVisitor(request);

        return new ApiResponse(
                true,
                "Visitor Successfully Added!",
                visitor
        );
    }


    // GET ALL VISITORS
    @GetMapping("/all")
    public ApiResponse getAllVisitor() {

        List<Visitor> visitors =
                service.getAllVisitor();

        return new ApiResponse(
                true,
                "All Visitors Fetched!",
                visitors
        );
    }


    // GET VISITOR BY ID
    @GetMapping("/{id}")
    public ApiResponse getVisitorById(
            @PathVariable long id) {

        Visitor visitor =
                service.getVisitorById(id);

        return new ApiResponse(
                true,
                "Visitor Successfully Fetched!",
                visitor
        );
    }


    // UPDATE VISITOR
    @PutMapping("/update/{id}")
    public ApiResponse updateVisitorById(
            @PathVariable long id,
            @RequestBody VisitorRequest request) {

        Visitor visitor =
                service.updateVisitorById(id, request);

        return new ApiResponse(
                true,
                "Visitor Successfully Updated!",
                visitor
        );
    }


    // DELETE VISITOR
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteVisitorById(
            @PathVariable long id) {

        service.deleteVisitorById(id);

        return new ApiResponse(
                true,
                "Visitor Successfully Deleted!",
                null
        );
    }
}