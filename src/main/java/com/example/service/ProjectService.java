package com.example.service;

import com.example.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    ProjectDTO getByProjectCode(String projectCode);

    List<ProjectDTO> listAllProjects();

    void save(ProjectDTO projectDTO);

    void delete(String projectCode);

    void complete(ProjectDTO projectDTO);

    void update(ProjectDTO projectDTO);
}
