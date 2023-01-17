package com.taskman.exception;

public class GetTasksException extends Exception{
    public GetTasksException() {
        super(String.format("Something went wrong."));
    }
}
