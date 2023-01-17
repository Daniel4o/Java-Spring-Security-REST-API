package com.taskman.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(long id) {
        super(String.format("Task with id = %d does not found", id));
    }
}
