package com.taskman.model.services;

import java.sql.Date;

public class TaskServiceModel {
    private Long id;
    private String description;
    private String comment;
    private Date date;

    public TaskServiceModel() {
    }

    public TaskServiceModel(Long id,  String description, String comment, Date date) {
        this.id = id;

        this.description = description;
        this.comment = comment;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
