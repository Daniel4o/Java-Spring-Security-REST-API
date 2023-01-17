package com.taskman.service;

import com.taskman.exception.EmployeeDeletionFails;
import com.taskman.exception.EmployeeNotFoundException;
import com.taskman.model.services.EmployeeServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    public List<EmployeeServiceModel> getEmployees() throws EmployeeNotFoundException;
    public EmployeeServiceModel getEmployeeById(long id) throws EmployeeNotFoundException;

    public EmployeeServiceModel addEmployee(EmployeeServiceModel task) throws EmployeeNotFoundException;

    public long deleteEmployeeById(long id) throws EmployeeNotFoundException, EmployeeDeletionFails;

    public void addTasks(Long employeeId, List<Long> tasksIds) throws EmployeeNotFoundException;
}
