package com.taskman.service;

import com.taskman.exception.*;
import com.taskman.model.services.TaskServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    public List<TaskServiceModel> getTasks() throws GetTasksException;
    public TaskServiceModel getTaskById(long id) throws TaskNotFoundException;

    public TaskServiceModel addTask(TaskServiceModel task) throws TaskNotFoundException, TaskSaveException;

    public TaskServiceModel updateTask(long id, TaskServiceModel task) throws TaskNotFoundException, TaskUpdateException;

    public  long deleteTaskById(long id) throws TaskNotFoundException, TaskDeletionFails;
}
