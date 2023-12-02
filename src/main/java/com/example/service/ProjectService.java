package com.example.service;

import com.example.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO projectDTO);

    void deleteByProjectCode(String projectCode);

    ProjectDTO getByProjectCode(String projectCode);

    void complete(ProjectDTO projectDTO);

    void update(ProjectDTO projectDTO);
}
