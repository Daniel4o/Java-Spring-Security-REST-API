package com.taskman.exception;

public class TaskSaveException extends Exception {
    public TaskSaveException(String location) {
        super(String.format("Something went wrong while trying to add a task in %t", location));
    }
}