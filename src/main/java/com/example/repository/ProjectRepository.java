package com.example.repository;

import com.example.entity.Project;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Transactional
    void deleteByProjectCode(String projectCode);

    Project findByProjectCode(String projectCode);

    List<Project> findAllByAssignedManager(User manager);


}
