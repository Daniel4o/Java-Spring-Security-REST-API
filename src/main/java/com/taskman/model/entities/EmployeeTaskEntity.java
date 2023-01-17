package com.taskman.model.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "trips_days")
public class EmployeeTaskEntity {
    @EmbeddedId
    private EmployeeTaskFK employeeTaskFK;

    public EmployeeTaskEntity() {
    }

    public EmployeeTaskEntity(EmployeeTaskFK employeeTaskFK) {
        this.employeeTaskFK = employeeTaskFK;
    }

    public EmployeeTaskFK getTripDayFK() {
        return employeeTaskFK;
    }

    public void setTripDayFK(EmployeeTaskFK employeeTaskFK) {
        this.employeeTaskFK = employeeTaskFK;
    }
}
