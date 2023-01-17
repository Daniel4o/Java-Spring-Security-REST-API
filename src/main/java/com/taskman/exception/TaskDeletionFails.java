package com.taskman.exception;

public class TaskDeletionFails extends Exception {
    public TaskDeletionFails(long id) {
        super(String.format("Something went wrong while deleting task with id = %d", id));
    }
}
