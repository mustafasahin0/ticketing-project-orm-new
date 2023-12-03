package com.example.service.impl;

import com.example.dto.TaskDTO;
import com.example.entity.Task;
import com.example.enums.Status;
import com.example.mapper.TaskMapper;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO taskDTO) {
        Task task = taskMapper.convertToEntity(taskDTO);
        task.setTaskStatus(Status.OPEN);
        task.setAssignedDate(LocalDate.now());

        taskRepository.save(task);
    }

    @Override
    public void delete(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        task.setIsDeleted(true);
        taskRepository.save(task);
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return taskMapper.converToDTO(taskRepository.findById(taskId).get());
    }

    @Override
    public void update(TaskDTO taskDTO) {
        Task task = taskRepository.findById(taskDTO.getId()).get();
        task.setTaskStatus(taskDTO.getTaskStatus());
        taskRepository.save(task);
    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNot(Status status) {
        return taskRepository.getAllByTaskStatusIsNot(status).stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus(Status status) {
        return taskRepository.getAllByTaskStatus(status).stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }
}
