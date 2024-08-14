package com.ideas2it.ems.controller;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    private static final Logger LOGGER = LogManager.getLogger(ProjectController.class);

    /**
     * Create the project based on the user request
     *
     * @param projectDto {@link ProjectDto}
     * @return ProjectDto with Http status Created.
     */
    @PostMapping
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto) {
        try {
            projectDto = projectService.addProject(projectDto);
            LOGGER.debug("Project Created with id {}", projectDto.getId());
            return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.warn("Error in laptop Created {}", projectDto.getName());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete the project by its id
     *
     * @param id : id of the project
     * @return projectDto with Http status No_Content.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable int id) {
        try {
            LOGGER.debug("Delete project by its id {}", id);
            return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.warn("warn in delete project by id {}", id);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the project details by its ID
     *
     * @param projectDto {@link ProjectDto}
     * @return ProjectDto with Http status Accepted.
     */
    @PutMapping
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        try {
            LOGGER.debug("Update project details by id {}", projectDto.getId());
            return new ResponseEntity<>(projectService.updateProject(projectDto), HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.warn("Error in update  project {}", projectDto.getName());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the employee details by project id
     *
     * @param id : id of the project
     * @return EmployeeDto {@link EmployeeDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<EmployeeDto>> retrieveEmployeesByProjectId(@PathVariable int id) {
        var employeeDto = projectService.retrieveEmployeesByProjectId(id);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get the all projects
     *
     * @return ProjectDto {@link ProjectDto}
     */
    @GetMapping
    public ResponseEntity<List<ProjectDto>> retrieveProjects() {
        try {
            return new ResponseEntity<>(projectService.retrieveProjects(), HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.warn("Error in view all projects");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Add project to employee
     *
     * @return EmployeeDto {@link EmployeeDto}.
     */
    @GetMapping("/{employeeId}/project/(projectId)")
    public ResponseEntity<ProjectDto> addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        try {
            
            return new ResponseEntity<>(projectService.addProjectToEmployee(employeeId, projectId), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.warn("Error in get all employees", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the project details by project id
     *
     * @param id : id of the project
     * @return ProjectDto {@link ProjectDto}
     */
    @GetMapping("/?project={id}")
    public ResponseEntity<ProjectDto> retrieveProjectById(@PathVariable int id) {
        var projectDto = projectService.retrieveProjectById(id);
        if (projectDto != null) {
            return ResponseEntity.ok(projectDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
