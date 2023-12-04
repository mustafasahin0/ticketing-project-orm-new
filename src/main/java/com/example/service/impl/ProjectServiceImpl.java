package com.example.service.impl;

import com.example.dto.ProjectDTO;
import com.example.dto.TaskDTO;
import com.example.dto.UserDTO;
import com.example.entity.Project;
import com.example.entity.Task;
import com.example.entity.User;
import com.example.enums.Status;
import com.example.mapper.ProjectMapper;
import com.example.mapper.TaskMapper;
import com.example.mapper.UserMapper;
import com.example.repository.ProjectRepository;
import com.example.repository.TaskRepository;
import com.example.service.ProjectService;
import com.example.service.TaskService;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;
    private final UserService userService;
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, UserMapper userMapper, UserService userService, TaskService taskService, TaskMapper taskMapper, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.userService = userService;
        this.taskService = taskService;
        this.taskMapper = taskMapper;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);

        projectRepository.save(projectMapper.converToEntity(projectDTO));
    }

    @Override
    public void delete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);

        project.setIsDeleted(true);
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());

        projectRepository.save(project);

        taskService.deleteByProject(projectMapper.convertToDTO(project));
    }

    @Override
    public ProjectDTO getByProjectCode(String projectCode) {
        return projectMapper.convertToDTO(projectRepository.findByProjectCode(projectCode));
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
    }

    @Override
    public void update(ProjectDTO projectDTO) {
        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());

        Project convertedProject = projectMapper.converToEntity(projectDTO);

        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);
    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {

        UserDTO currentUserDTO = userService.findByUserName("harold@manager.com");

        User user = userMapper.convertToEntity(currentUserDTO);

        List<Project> list = projectRepository.findAllByAssignedManager(user);

        return list.stream().map(project -> {
            ProjectDTO projectDTO = projectMapper.convertToDTO(project);

            projectDTO.setUnfinishedTaskCounts(taskService.totalNotCompletedTask(project.getProjectCode()));
            projectDTO.setCompleteTaskCounts(taskService.totalCompletedTask(project.getProjectCode()));

            return projectDTO;
        }).collect(Collectors.toList());
    }
}
