package com.taskman.service.Impl;

import com.taskman.exception.*;
import com.taskman.repository.TaskRepository;
import com.taskman.model.entities.TaskEntity;
import com.taskman.model.services.TaskServiceModel;
import com.taskman.service.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TaskServiceModel> getTasks() throws GetTasksException {
        List<TaskServiceModel> tasks = taskRepository.getTaskEntities()
                .stream()
                .map((task) -> modelMapper.map(task, TaskServiceModel.class))
                .collect(Collectors.toList());
        return tasks;
    }

    @Override
    public TaskServiceModel getTaskById(long id) throws TaskNotFoundException {
        return taskRepository.findById(id)
                .map(task -> modelMapper.map(task, TaskServiceModel.class))
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public TaskServiceModel addTask(TaskServiceModel taskInput) throws TaskNotFoundException, TaskSaveException {
        TaskEntity task = modelMapper.map(taskInput, TaskEntity.class);
        TaskEntity result = taskRepository.save(task);

        return getTaskById(task.getId());
    }

    @Override
    public TaskServiceModel updateTask(long id, TaskServiceModel task) throws TaskNotFoundException, TaskUpdateException {
        Optional<TaskEntity> taskToBeUpdateOpt = taskRepository.findById(id);

        if (taskToBeUpdateOpt.isEmpty()) {
            throw new TaskNotFoundException(id);
        }

        TaskEntity taskUpdateObject = modelMapper.map(task, TaskEntity.class);
        TaskEntity taskToBeUpdated = taskToBeUpdateOpt.get();

        taskToBeUpdated.setEmployee(taskUpdateObject.getEmployee());
        taskToBeUpdated.setDate(taskUpdateObject.getDate());
        taskToBeUpdated.setDescription(taskUpdateObject.getDescription());
        taskToBeUpdated.setComment(taskUpdateObject.getComment());

        taskRepository.save(taskToBeUpdated);
        Optional<TaskEntity> updatedTask = taskRepository.findById(id);

        if (updatedTask.isEmpty()) {
            throw new TaskUpdateException(id);
        }

        return modelMapper.map(updatedTask, TaskServiceModel.class);
    }

    @Override
    public long deleteTaskById(long id) throws TaskNotFoundException, TaskDeletionFails {
        Optional<TaskEntity> taskToBeDeleteOpt = taskRepository.findById(id);

        if (taskToBeDeleteOpt.isEmpty()) {
            throw new TaskNotFoundException(id);
        }

        taskRepository.delete(taskToBeDeleteOpt.get());

        if(taskRepository.findById(id).isPresent()) {
            throw new TaskDeletionFails(id);
        }

        return id;
    }
}
