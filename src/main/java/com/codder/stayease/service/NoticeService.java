package com.codder.stayease.service;

import com.codder.stayease.Exception.ResourceNotFoundException;
import com.codder.stayease.dto.NoticeRequest;
import com.codder.stayease.entity.Notice;
import com.codder.stayease.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepo;


    // ADD NOTICE
    public Notice addNotice(NoticeRequest request) {

        Notice notice = new Notice();

        notice.setTitle(request.getTitle());
        notice.setDescription(request.getDescription());
        notice.setNoticeDate(request.getNoticeDate());
        notice.setExpiryDate(request.getExpiryDate());
        notice.setPriority(request.getPriority());
        notice.setStatus(request.getStatus());
        notice.setCreatedBy(request.getCreatedBy());

        return noticeRepo.save(notice);
    }


    // GET ALL NOTICES
    public List<Notice> getAllNotice() {

        return noticeRepo.findAll();
    }


    // GET NOTICE BY ID
    public Notice getNoticeById(long id) {

        return noticeRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notice Not Found!"));
    }


    // UPDATE NOTICE
    public Notice updateNoticeById(
            long id,
            NoticeRequest request) {

        Notice notice = noticeRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notice Not Found!"));

        notice.setTitle(request.getTitle());
        notice.setDescription(request.getDescription());
        notice.setNoticeDate(request.getNoticeDate());
        notice.setExpiryDate(request.getExpiryDate());
        notice.setPriority(request.getPriority());
        notice.setStatus(request.getStatus());
        notice.setCreatedBy(request.getCreatedBy());

        return noticeRepo.save(notice);
    }


    // DELETE NOTICE
    public void deleteNoticeById(long id) {

        Notice notice = noticeRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notice Not Found!"));

        noticeRepo.delete(notice);
    }
}