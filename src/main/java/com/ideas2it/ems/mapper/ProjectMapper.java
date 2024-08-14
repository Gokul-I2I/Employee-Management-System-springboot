package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.model.Project;

/**
 * Object to Data Transfer Object and Dto to Object Conversion
 */
public class ProjectMapper {

    /**
     * Convert the Project to the ProjectDto
     * @param project {@link Project}
     * @return ProjectDto {@link ProjectDto}
     */
    public static ProjectDto convertDto(Project project) {

        return ProjectDto.builder()
                .id(project.getProjectId())
                .name(project.getProjectName())
                .build();
    }

    /**
     * Convert the Project to the ProjectDto
     * @param projectDto {@link ProjectDto}
     * @return  project {@link Project}
     */
    public static Project convertObject(ProjectDto projectDto) {

        return Project.builder()
                .projectId(projectDto.getId())
                .projectName(projectDto.getName())
                .build();
    }
}

