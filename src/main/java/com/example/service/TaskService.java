package com.example.service;

import com.example.dto.TaskDTO;
import com.example.entity.Task;
import com.example.enums.Status;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    void save(TaskDTO taskDTO);

    void deleteById(Long taskId);

    TaskDTO findTaskById(Long taskId);

    void update(TaskDTO taskDTO);

    List<TaskDTO> findAllTasksByStatusIsNot(Status status);

    List<TaskDTO> findAllTasksByStatus(Status status);
}
