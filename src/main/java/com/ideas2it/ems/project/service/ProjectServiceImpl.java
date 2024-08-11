package com.ideas2it.ems.project.service;

import com.ideas2it.ems.project.dao.ProjectDao;
import com.ideas2it.ems.project.dto.ProjectDto;
import com.ideas2it.ems.project.mapper.Mapper;
import com.ideas2it.ems.model.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectDao projectDao;

    @Override
    public ProjectDto addProject(Project project) {
        return Mapper.convertDto(projectDao.save(project));
    }

    public ProjectDto deleteProject(Project project) {
        return Mapper.convertDto(projectDao.saveAndFlush(project));
    }

    public ProjectDto updateProject(Project project) {
        return Mapper.convertDto(projectDao.saveAndFlush(project));
    }

    public ProjectDto retrieveProjectById(int id) {
        return Mapper.convertDto(Objects.requireNonNull(projectDao.findById(id).orElse(null)));
    }

    public List<Project> retrieveProjects() {
        return projectDao.findAll();
    }
}
