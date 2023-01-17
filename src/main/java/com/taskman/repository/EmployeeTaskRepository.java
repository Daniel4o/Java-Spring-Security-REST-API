package com.taskman.repository;

import com.taskman.model.entities.EmployeeTaskEntity;
import com.taskman.model.entities.EmployeeTaskFK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTaskEntity, EmployeeTaskFK> {

   // @Query("SELECT td FROM EmployeeTaskEntity td where td.employeeId = id")
   // List<EmployeeTaskEntity> getEmployeeTaskByEmployeeId(@Param("id") long id);
}
