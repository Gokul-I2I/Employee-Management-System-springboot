package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.mapper.ProjectMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private EmployeeService employeeService;
    private static final Logger LOGGER = LogManager.getLogger(LaptopServiceImpl.class);

    @Override
    public ProjectDto addProject(ProjectDto projectDto) {
        if (projectDao.existsByProjectName(projectDto.getName())) {
            LOGGER.warn("Project Already Found ");
            throw new MyException("Project Already found : " + projectDto.getName());
        }
        return ProjectMapper.convertDto(projectDao.save(ProjectMapper.convertObject(projectDto)));
    }

    @SneakyThrows
    @Override
    public ProjectDto deleteProject(int id) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(id);
        if (project == null) {
            LOGGER.warn("Project Not Found {}", id);
            throw new NoSuchFieldException("Project not found : " + id);
        } else if (project.isDeleted()) {
            LOGGER.warn("Project Not Found ");
            throw new MyException("Project Not Found " + id);
        }
        project.setDeleted(true);
        return ProjectMapper.convertDto(projectDao.saveAndFlush(project));
    }

    @SneakyThrows
    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        if (projectDao.existsByProjectName(projectDto.getName())) {
            LOGGER.warn("Project Already Found with same name {}", projectDto.getName());
            throw new MyException("Project Already found : " + projectDto.getName());
        }
        var project = projectDao.findByProjectIdAndIsDeletedFalse(projectDto.getId());
        if (project == null) {
            LOGGER.warn("Project not Found {}",projectDto.getId());
            throw new NoSuchFieldException("Project not found :" + projectDto.getId());
        }
        project.setProjectName(projectDto.getName());
        return ProjectMapper.convertDto(projectDao.save(project));
    }

    @SneakyThrows
    @Override
    public List<EmployeeDto> retrieveEmployeesByProjectId(int id) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(id);
        if (project == null) {
            LOGGER.warn("Project Not Found {}", id);
            throw new NoSuchFieldException("Project not Found : " + id);
        }
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for (Employee employee : project.getEmployees()) {
            if (!employee.isDeleted()) {
                employeeDtos.add(EmployeeMapper.convertDto(employee));
            }
        }
        if (employeeDtos.isEmpty()) {
            LOGGER.warn("Employees not Found in this project id {}", id);
            throw new NoSuchFieldException("Employees not found in project id : " + id);
        }
        return employeeDtos;
    }

    @SneakyThrows
    @Override
    public List<ProjectDto> retrieveProjects() {
        List<ProjectDto> projectDtos = new ArrayList<>();
        var projects = projectDao.findByIsDeletedFalse();
        if (projects.isEmpty()) {
            LOGGER.warn("Project Not Found ");
            throw new NoSuchFieldException("No Projects Available");
        }
        for (Project project : projects) {
            projectDtos.add(ProjectMapper.convertDto(project));
        }
        return projectDtos;
    }

    @SneakyThrows
    @Override
    public ProjectDto retrieveProjectById(int id) {
        var project = projectDao.findByProjectIdAndIsDeletedFalse(id);
        if (project == null) {
            LOGGER.warn("Project Not Found ");
            throw new NoSuchFieldException("Project not Found");
        }
        return ProjectMapper.convertDto(project);
    }

    @SneakyThrows
    @Override
    public ProjectDto addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        if (project == null) {
            LOGGER.warn("Project Not Found {}", projectId);
            throw new NoSuchFieldException("Project not Found : " + projectId);
        }
        Employee employee = EmployeeMapper.convertObject(employeeService.retrieveEmployeeById(employeeId));
        if (employee == null) {
            LOGGER.warn("Employee Not Found in this id {}", employeeId);
            throw new NoSuchFieldException("Employees not found in project id : " + employeeId);
        }
        project.getEmployees().add(employee);
        return ProjectMapper.convertDto(projectDao.saveAndFlush(project));
    }

    @SneakyThrows
    @Override
    public ProjectDto removeProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        Project project = projectDao.findByProjectIdAndIsDeletedFalse(projectId);
        if (project == null) {
            LOGGER.warn("Project not Found {}", projectId);
            throw new NoSuchFieldException("Project not Found : " + projectId);
        }
        Employee employee = EmployeeMapper.convertObject(employeeService.retrieveEmployeeById(employeeId));
        if (employee == null) {
            LOGGER.warn("Employee not Found {}", employeeId);
            throw new NoSuchFieldException("Employees not found in project id : " + employeeId);
        }
        project.getEmployees().remove(employee);
        return ProjectMapper.convertDto(projectDao.saveAndFlush(project));
    }
}
