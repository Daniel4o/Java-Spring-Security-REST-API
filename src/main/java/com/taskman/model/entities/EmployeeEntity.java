package com.taskman.model.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "trips")
public class EmployeeEntity extends BaseEntity{
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="trips_tasks",
            joinColumns = { @JoinColumn(name = "trip_id") },
            inverseJoinColumns = { @JoinColumn(name = "task_id")}
    )
    private List<TaskEntity> tasks;
    @ManyToMany
    private List<FileEntity> pictures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public List<FileEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<FileEntity> pictures) {
        this.pictures = pictures;
    }
}
