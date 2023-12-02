package com.example.service.impl;

import com.example.dto.ProjectDTO;
import com.example.entity.Project;
import com.example.enums.Status;
import com.example.mapper.ProjectMapper;
import com.example.repository.ProjectRepository;
import com.example.service.ProjectService;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectRepository.save(projectMapper.converToEntity(projectDTO));
    }

    @Override
    public void deleteByProjectCode(String projectCode) {
        projectRepository.deleteByProjectCode(projectCode);
    }

    @Override
    public ProjectDTO getByProjectCode(String projectCode) {
        return projectMapper.convertToDTO(projectRepository.findByProjectCode(projectCode));
    }

    @Override
    public void complete(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.COMPLETE);
    }

    @Override
    public void update(ProjectDTO projectDTO) {
       
    }
}
