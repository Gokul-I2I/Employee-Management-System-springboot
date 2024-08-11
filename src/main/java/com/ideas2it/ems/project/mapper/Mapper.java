package com.ideas2it.ems.project.mapper;

import com.ideas2it.ems.project.dto.ProjectDto;
import com.ideas2it.ems.model.Project;

public class Mapper {
    public static ProjectDto convertDto(Project project) {

        return ProjectDto.builder()
                .id(project.getProjectId())
                .name(project.getProjectName())
                .build();
    }

    public static Project convertObject(ProjectDto projectDto) {

        return Project.builder()
                .projectId(projectDto.getId())
                .projectName(projectDto.getName())
                .build();

    }
}
