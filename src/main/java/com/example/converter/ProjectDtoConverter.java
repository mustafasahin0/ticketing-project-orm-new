package com.example.converter;

import com.example.dto.ProjectDTO;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ProjectDtoConverter implements Converter<String, ProjectDTO> {
    @Override
    public ProjectDTO convert(String source) {
        return null;
    }

//    ProjectService projectService;
//
//    //injection
//    public ProjectDtoConverter(ProjectService projectService) {
//        this.projectService = projectService;
//    }
//
//    @Override
//    public ProjectDTO convert(String source) {
//
//        if (source == null || source.equals("")) {
//            return null;
//        }
//
//        return projectService.findById(source);
//
//    }

}
