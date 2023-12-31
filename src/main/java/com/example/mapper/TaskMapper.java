package com.example.mapper;

import com.example.dto.TaskDTO;
import com.example.entity.Task;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    private final ModelMapper modelMapper;

    public TaskMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Task convertToEntity(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }

    public TaskDTO converToDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }
}
