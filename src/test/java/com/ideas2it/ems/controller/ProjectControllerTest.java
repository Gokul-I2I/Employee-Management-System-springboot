package com.ideas2it.ems.controller;

import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {
    @Mock
    ProjectService projectService;

    @InjectMocks
    ProjectController projectController;

    private ProjectDto projectDto;

    @BeforeEach
    void setUp() {
        projectDto = ProjectDto.builder()
                .id(1)
                .name("it")
                .build();
    }

    @Test
    void testAddProject() {
        when(projectService.addProject(projectDto)).thenReturn(projectDto);
        ResponseEntity<ProjectDto> response = projectController.addProject(projectDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(projectDto, response.getBody());
        verify(projectService, times(1)).addProject(projectDto);
    }
    @Test
    void testDeleteProject() {
        when(projectService.deleteProject(projectDto.getId())).thenReturn(projectDto);
        ResponseEntity<ProjectDto> response = projectController.deleteProject(projectDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projectService, times(1)).deleteProject(projectDto.getId());
    }

    @Test
    void testUpdateProject() {
        when(projectService.updateProject(projectDto)).thenReturn(projectDto);
        ResponseEntity<ProjectDto> response = projectController.updateProject(projectDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectService, times(1)).updateProject(projectDto);
    }

    @Test
    void testRetrieveProjectById() {
        when(projectService.retrieveProjectById(projectDto.getId())).thenReturn(projectDto);
        ResponseEntity<ProjectDto> response = projectController.retrieveProjectById(projectDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectService, times(1)).retrieveProjectById(projectDto.getId());
    }

    @Test
    void testRetrieveProjects() {
        when(projectService.retrieveProjects()).thenReturn(List.of(projectDto));
        ResponseEntity<List<ProjectDto>> response = projectController.retrieveProjects();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(projectService, times(1)).retrieveProjects();
    }
}
