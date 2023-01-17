package com.taskman.exception;

public class TaskUpdateException extends Exception{
    public TaskUpdateException(long id) {
        super(String.format("Task with id = %d update failed!", id));
    }
}
