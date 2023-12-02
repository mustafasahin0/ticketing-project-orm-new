package com.example.entity;

import com.example.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {


    private String projectName;


    private String projectCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedManager;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    private String projectDetail;

    @Enumerated(EnumType.STRING)
    private Status projectStatus;

    private int completeTaskCounts;
    private int unfinishedTaskCounts;
}
