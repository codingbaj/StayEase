package com.codder.stayease.controller;

import com.codder.stayease.dto.NoticeRequest;
import com.codder.stayease.entity.Notice;
import com.codder.stayease.response.ApiResponse;
import com.codder.stayease.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService service;


    // ADD NOTICE
    @PostMapping("/add")
    public ApiResponse addNotice(
            @RequestBody NoticeRequest request) {

        Notice notice = service.addNotice(request);

        return new ApiResponse(
                true,
                "Notice Successfully Added!",
                notice
        );
    }


    // GET ALL NOTICES
    @GetMapping("/all")
    public ApiResponse getAllNotice() {

        List<Notice> notices =
                service.getAllNotice();

        return new ApiResponse(
                true,
                "All Notices Fetched!",
                notices
        );
    }


    // GET NOTICE BY ID
    @GetMapping("/{id}")
    public ApiResponse getNoticeById(
            @PathVariable long id) {

        Notice notice =
                service.getNoticeById(id);

        return new ApiResponse(
                true,
                "Notice Successfully Fetched!",
                notice
        );
    }


    // UPDATE NOTICE
    @PutMapping("/update/{id}")
    public ApiResponse updateNoticeById(
            @PathVariable long id,
            @RequestBody NoticeRequest request) {

        Notice notice =
                service.updateNoticeById(id, request);

        return new ApiResponse(
                true,
                "Notice Successfully Updated!",
                notice
        );
    }


    // DELETE NOTICE
    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteNoticeById(
            @PathVariable long id) {

        service.deleteNoticeById(id);

        return new ApiResponse(
                true,
                "Notice Successfully Deleted!",
                null
        );
    }
}