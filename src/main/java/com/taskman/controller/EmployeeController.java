package com.taskman.controller;

import com.taskman.exception.EmployeeDeletionFails;
import com.taskman.exception.EmployeeNotFoundException;
import com.taskman.model.services.EmployeeServiceModel;
import com.taskman.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeServiceModel>> getEmployees() throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeServiceModel> getEmployeeById(@PathVariable("id") long id) throws EmployeeNotFoundException, EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeServiceModel> addEmployee(@RequestBody() EmployeeServiceModel employee) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteEmployeeById(@PathVariable("id") long id) throws EmployeeNotFoundException, EmployeeDeletionFails {
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }

    @PutMapping("/days/{id}")
    public void addDays(@PathVariable("id") long id, @RequestBody List<Long> daysIds) throws EmployeeNotFoundException {
        employeeService.addDays(id, daysIds);
    }
}
