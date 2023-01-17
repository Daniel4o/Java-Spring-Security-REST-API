package com.taskman.model.services;

import java.util.Date;
import java.util.List;

public class EmployeeTaskServiceModel {
    private long taskId;
    private Date data;

    public EmployeeTaskServiceModel() {
    }

    public EmployeeTaskServiceModel(long taskId, Date data) {
        this.taskId = taskId;
        this.data = data;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
