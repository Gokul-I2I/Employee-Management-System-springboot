package com.ideas2it.ems.project.service;

import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.project.dto.ProjectDto;

import java.util.List;
import java.util.Objects;

public interface ProjectService {
    ProjectDto addProject(Project project);

    ProjectDto deleteProject(Project project);

    ProjectDto updateProject(Project project);

    ProjectDto retrieveProjectById(int id);

    List<Project> retrieveProjects();
}
