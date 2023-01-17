package com.taskman.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(long id) {
        super(String.format("Employee with id = %d does not found", id));
    }
}
