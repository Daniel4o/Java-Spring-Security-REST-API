
package com.taskman.exception;

public class EmployeeDeletionFails extends Exception{
    public EmployeeDeletionFails(long id) {
        super(String.format("Something went wrong while deleting employee with id = %d", id));
    }
}
