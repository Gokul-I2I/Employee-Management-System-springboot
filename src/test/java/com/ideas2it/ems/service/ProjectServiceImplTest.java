package com.ideas2it.ems.service;

import com.ideas2it.ems.dao.ProjectDao;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Project;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceImplTest {
    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private ProjectDto projectDto;
    private Project project;
    private Project project1;
    private Department department;
    private Employee employee;
    private Employee employee1;

    @BeforeEach
    void setUp() {
        Laptop laptop = Laptop.builder()
                .laptopName("thinkpad")
                .build();

        project = Project.builder()
                .projectId(1)
                .projectName("web3")
                .isDeleted(false)
                .build();

        project1 = Project.builder()
                .projectId(2)
                .projectName("web")
                .isDeleted(true)
                .build();

        projectDto = ProjectDto.builder()
                .id(1)
                .name("web3")
                .build();

        department = Department.builder()
                .departmentId(1)
                .departmentName("it")

                .build();

        employee = Employee.builder()
                .id(1)
                .name("gokul")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .laptop(laptop)
                .department(department)
                .projects(Set.of(project))
                .isDeleted(true)
                .build();

        employee1 = Employee.builder()
                .id(2)
                .name("kishore")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .isDeleted(false)
                .department(department)
                .projects(Set.of(project))
                .laptop(laptop)
                .build();
    }

    @Test
    void testAddProject() {
        when(projectDao.existsByProjectName(projectDto.getName())).thenReturn(false);
        when(projectDao.save(any(Project.class))).thenReturn(project);
        ProjectDto createdProject = projectService.addProject(projectDto);
        assertNotNull(createdProject);
        assertEquals(projectDto.getName(), createdProject.getName());
    }

    @Test
    void testDeleteProject() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(projectDto.getId())).thenReturn(project);
        when(projectDao.saveAndFlush(any(Project.class))).thenReturn(project);
        ProjectDto result = projectService.deleteProject(projectDto.getId());
        assertTrue(project.isDeleted());
    }

    @Test
    void testDeleteProject_InvalidId() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(4)).thenReturn(project);
        assertThrows(NullPointerException.class, () -> projectService.deleteProject(4));
    }

    @Test
    void testDeleteProject_AlreadyDeleted() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(2)).thenReturn(project);
        assertThrows(NullPointerException.class, () -> projectService.deleteProject(2));
    }

    @Test
    void testAddProject_AlreadyPresent() {
        when(projectDao.existsByProjectName(project.getProjectName())).thenReturn(true);
        assertThrows(MyException.class, () -> projectService.addProject(projectDto));
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = List.of(project);
        when(projectDao.findByIsDeletedFalse()).thenReturn(projects);
        List<ProjectDto> result = projectService.retrieveProjects();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateProject() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(projectDto.getId())).thenReturn(project);
        when(projectDao.existsByProjectName(project.getProjectName())).thenReturn(false);
        project.setProjectName("fb");
        when(projectDao.save(any(Project.class))).thenReturn(project);
        var result = projectService.updateProject(projectDto);
        assertNotNull(result);
        assertEquals(projectDto.getName(), result.getName());
    }

    @Test
    void testRetrieveEmployeesByProjectId() {
        project.setEmployees(Set.of(employee, employee1));
        when(projectDao.findByProjectIdAndIsDeletedFalse(projectDto.getId())).thenReturn(project);
        List<EmployeeDto> employees = projectService.retrieveEmployeesByProjectId(projectDto.getId());
        assertEquals(1, employees.size());
    }

    @Test
    void testRetrieveProjectById() {
        when(projectDao.findByProjectIdAndIsDeletedFalse(projectDto.getId())).thenReturn(project);
        var projects = projectService.retrieveProjectById(projectDto.getId());
        assertEquals(projects.getName(), projectDto.getName());
    }

}
