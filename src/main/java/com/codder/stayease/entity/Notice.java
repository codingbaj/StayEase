package com.codder.stayease.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDate noticeDate;

    private LocalDate expiryDate;

    private String priority;

    private String status;

    private String createdBy;


    public Notice(String title,
                  String description,
                  LocalDate noticeDate,
                  LocalDate expiryDate,
                  String priority,
                  String status,
                  String createdBy) {

        this.title = title;
        this.description = description;
        this.noticeDate = noticeDate;
        this.expiryDate = expiryDate;
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
    }


    public Notice() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }


    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }


    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}