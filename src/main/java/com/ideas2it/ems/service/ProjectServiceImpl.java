package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.mapper.ProjectMapper;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        var project = ProjectMapper.convertObject(projectDto);
        return ProjectMapper.convertDto(projectDao.save(project));
    }

    @Override
    public ProjectDto deleteProject(int id) {
        var project = projectDao.getReferenceById(id);
        project.setDeleted(true);
        return ProjectMapper.convertDto(projectDao.saveAndFlush(project));
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        return ProjectMapper.convertDto(projectDao.saveAndFlush(ProjectMapper.convertObject(projectDto)));
    }

    @Override
    public List<EmployeeDto> retrieveEmployeesByProjectId(int id) {
        var project = projectDao.findByProjectIdAndIsDeletedFalse(id);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : project.getEmployees()) {
            if (!employee.isDeleted()) {
                employeeDtos.add(EmployeeMapper.convertDto(employee));
            }
        }
        return employeeDtos;
    }

    @Override
    public List<ProjectDto> retrieveProjects() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        var projects = projectDao.findByIsDeletedFalse();
        for (Project project : projects) {
            projectDtos.add(ProjectMapper.convertDto(project));
        }
        return projectDtos;
    }

    @Override
    public ProjectDto retrieveProjectById(int id) {
        return ProjectMapper.convertDto(projectDao.findByProjectIdAndIsDeletedFalse(id));
    }

    @Override
    public ProjectDto addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        Employee employee = EmployeeMapper.convertObject(employeeService.retrieveEmployeeById(employeeId));
        project.getEmployees().add(employee);
        return ProjectMapper.convertDto(project);
    }
}
