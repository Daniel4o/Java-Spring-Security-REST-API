package com.taskman.service.Impl;

import com.taskman.exception.EmployeeDeletionFails;
import com.taskman.exception.EmployeeNotFoundException;
import com.taskman.model.entities.EmployeeEntity;
import com.taskman.repository.TaskRepository;
import com.taskman.repository.EmployeeTaskRepository;
import com.taskman.repository.EmployeeRepository;
import com.taskman.model.entities.TaskEntity;
import com.taskman.model.services.EmployeeServiceModel;
import com.taskman.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final TaskRepository taskRepository;
    private final EmployeeTaskRepository employeeTaskRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            TaskRepository taskRepository,
            EmployeeTaskRepository employeeTaskRepository,
            ModelMapper modelMapper
    ) {
        this.employeeRepository = employeeRepository;
        this.taskRepository = taskRepository;
        this.employeeTaskRepository = employeeTaskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeServiceModel> getEmployees() throws EmployeeNotFoundException {
        List<EmployeeServiceModel> employees = employeeRepository.getEmployeeEntities()
                .stream()
                .map((employee) -> modelMapper.map(employee, EmployeeServiceModel.class))
                .collect(Collectors.toList());
        return employees;
    }

    @Override
    public EmployeeServiceModel getEmployeeById(long id) throws EmployeeNotFoundException {
        return employeeRepository.findById(id)
                .map(employee -> modelMapper.map(employee, EmployeeServiceModel.class))
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public EmployeeServiceModel addEmployee(EmployeeServiceModel employeeInput) throws EmployeeNotFoundException {
        EmployeeEntity employee = modelMapper.map(employeeInput, EmployeeEntity.class);
        EmployeeEntity result = employeeRepository.save(employee);

        return getEmployeeById(result.getId());
    }

    @Override
    public long deleteEmployeeById(long id) throws EmployeeNotFoundException, EmployeeDeletionFails {
        Optional<EmployeeEntity> employeeToBeDeleteOpt = employeeRepository.findById(id);

        if (employeeToBeDeleteOpt.isEmpty()) {
            throw new EmployeeNotFoundException(id);
        }

        employeeRepository.delete(employeeToBeDeleteOpt.get());

        if(employeeRepository.findById(id).isPresent()) {
            throw new EmployeeDeletionFails(id);
        }

        return id;
    }

    @Override
    @Transactional
    public void addTasks(Long employeeId, List<Long> tasksIds) throws EmployeeNotFoundException {
        List<TaskEntity> tasks = taskRepository.getTasksByIds(tasksIds);
        EmployeeEntity employee = modelMapper.map(getEmployeeById(employeeId), EmployeeEntity.class);

        tasks.forEach(task -> task.getEmployees().add(employee));
        employee.setTasks(tasks);

        employeeRepository.saveAndFlush(employee);
    }
}
