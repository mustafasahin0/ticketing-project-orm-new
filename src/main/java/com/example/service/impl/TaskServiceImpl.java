package com.example.service.impl;

import com.example.dto.ProjectDTO;
import com.example.dto.TaskDTO;
import com.example.entity.Task;
import com.example.entity.User;
import com.example.enums.Status;
import com.example.mapper.ProjectMapper;
import com.example.mapper.TaskMapper;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper,
                           UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(TaskDTO taskDTO) {
        taskDTO.setTaskStatus(Status.OPEN);
        taskDTO.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity(taskDTO);
        taskRepository.save(task);
    }

    @Override
    public void delete(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            task.get().setIsDeleted(true);
            taskRepository.save(task.get());
        }
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return taskMapper.converToDTO(taskRepository.findById(taskId).get());
    }

    @Override
    public void update(TaskDTO taskDTO) {
        Optional<Task> existedTask = taskRepository.findById(taskDTO.getId());
        Task updatedTask = taskMapper.convertToEntity(taskDTO);

        if (existedTask.isPresent()) {
            updatedTask.setId(existedTask.get().getId());
            updatedTask.setTaskStatus(taskDTO.getTaskStatus() == null ? existedTask.get().getTaskStatus() : taskDTO.getTaskStatus());
            updatedTask.setAssignedDate(existedTask.get().getAssignedDate());
            taskRepository.save(updatedTask);
        }
    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNot(Status status) {
        User loggedInUser = userRepository.findByUserName("john@employee.com");

        List<Task> taskList = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, loggedInUser);
        return taskList.stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus(Status status) {
        User loggedInUser = userRepository.findByUserName("john@employee.com");

        List<Task> taskList = taskRepository.findAllByTaskStatusAndAssignedEmployee(status, loggedInUser);
        return taskList.stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByProjectCode(String projectCode) {
        return taskRepository.listAllByProjectProjectCode(projectCode).stream().map(taskMapper::converToDTO).collect(Collectors.toList());
    }

    @Override
    public int totalNotCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTasks(projectCode);
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }

    @Override
    public void deleteByProject(ProjectDTO projectDTO) {
        List<TaskDTO> taskList = getAllByProject(projectDTO).stream().map(taskMapper::converToDTO).collect(Collectors.toList());
        taskList.forEach(task -> delete(task.getId()));
    }

    @Override
    public void completeByProject(ProjectDTO projectDTO) {
        List<TaskDTO> taskList = getAllByProject(projectDTO).stream().map(taskMapper::converToDTO).collect(Collectors.toList());
        taskList.forEach(taskDTO -> {
            taskDTO.setTaskStatus(Status.COMPLETE);
            updateStatus(taskDTO);
        });
    }

    @Override
    public void updateStatus(TaskDTO taskDTO) {

        Optional<Task> task = taskRepository.findById(taskDTO.getId());

        if (task.isPresent()) {
            task.get().setTaskStatus(taskDTO.getTaskStatus());
            taskRepository.save(task.get());
        }
    }

    private List<Task> getAllByProject(ProjectDTO projectDTO) {
        return taskRepository.findAllByProject(projectMapper.converToEntity(projectDTO));
    }
}
