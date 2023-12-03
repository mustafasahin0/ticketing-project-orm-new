package com.example.entity;

import com.example.dto.ProjectDTO;
import com.example.dto.UserDTO;
import com.example.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "is_deleted=false")
@Getter
@Setter
public class Task extends BaseEntity {

    private String taskSubject;
    private String taskDetail;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;
    @Column(columnDefinition = "DATE")
    private LocalDate assignedDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

}
