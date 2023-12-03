package com.example.repository;

import com.example.entity.Task;
import com.example.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getAllByTaskStatusIsNot(Status status);

    List<Task> getAllByTaskStatus(Status status);

}