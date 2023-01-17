package com.taskman.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class TaskEntity extends BaseEntity implements Serializable {
    private String location;
    private String description;
    private String comment;
    private Date date;

    @ManyToMany(
            mappedBy = "tasks",
            fetch = FetchType.EAGER,
            cascade=CascadeType.MERGE)
    @JsonIgnore
    private List<EmployeeEntity> employees;
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
