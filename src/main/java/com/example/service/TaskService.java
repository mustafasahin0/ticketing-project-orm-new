package com.example.service;

import com.example.dto.ProjectDTO;
import com.example.dto.TaskDTO;
import com.example.enums.Status;

import java.util.List;

public interface TaskService {

    TaskDTO findById(Long taskId);

    List<TaskDTO> listAllTasks();

    void save(TaskDTO taskDTO);

    void update(TaskDTO taskDTO);

    void delete(Long taskId);

    List<TaskDTO> findAllTasksByStatusIsNot(Status status);

    List<TaskDTO> findAllTasksByStatus(Status status);

    List<TaskDTO> findAllTasksByProjectCode(String projectCode);

    int totalNotCompletedTask(String projectCode);

    int totalCompletedTask(String projectCode);

    void deleteByProject(ProjectDTO projectDTO);
}
