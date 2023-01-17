package com.taskman.controller;

import com.taskman.exception.*;
import com.taskman.exception.*;
import com.taskman.model.services.TaskServiceModel;
import com.taskman.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskServiceModel>> getTasks(Principal pr) throws GetTasksException {
        System.out.println(pr.getName());
        System.out.println(pr.getClass());
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskServiceModel> getTaskById(@PathVariable("id") long id) throws TaskNotFoundException {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping()
    public ResponseEntity<TaskServiceModel> addTask(@RequestBody() TaskServiceModel task) throws TaskNotFoundException, TaskSaveException {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskServiceModel> updateTask(
            @PathVariable("id") long id,
            @RequestBody() TaskServiceModel task) throws TaskNotFoundException, TaskUpdateException {
        return  new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Long> deleteTaskById(@PathVariable("id") long id) throws TaskNotFoundException, TaskDeletionFails {
        return  new ResponseEntity<>(taskService.deleteTaskById(id), HttpStatus.OK);
    }
}
