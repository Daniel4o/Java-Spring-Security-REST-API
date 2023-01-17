package com.taskman.repository;

import com.taskman.model.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT d FROM TaskEntity d")
    List<TaskEntity> getTaskEntities();

    Optional<TaskEntity> findById(int id);

    @Query("SELECT d FROM TaskEntity d where d.id in(:pIds)")
    List<TaskEntity> getTasksByIds(@Param("pIds") List<Long> ids);
}
