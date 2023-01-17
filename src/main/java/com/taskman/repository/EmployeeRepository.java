package com.taskman.repository;

import com.taskman.model.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT t FROM EmployeeEntity t")
    List<EmployeeEntity> getEmployeeEntities();
    Optional<EmployeeEntity> findById(int id);
}
