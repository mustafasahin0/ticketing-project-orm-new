package com.example.repository;

import com.example.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Transactional
    void deleteByProjectCode(String projectCode);

    Project findByProjectCode(String projectCode);


}
