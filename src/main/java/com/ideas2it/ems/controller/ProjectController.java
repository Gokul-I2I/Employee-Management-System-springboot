package com.ideas2it.ems.controller;

import java.util.List;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.ProjectDto;
import com.ideas2it.ems.service.ProjectService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProjectDto> addProject(@RequestBody ProjectDto projectDto) {projectDto = projectService.addProject(projectDto);
        LOGGER.info("Project Created with id {}", projectDto.getId());
        return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
    }

    /**
     * Delete the project by its id
     *
     * @param id : id of the project
     * @return projectDto with Http status No_Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ProjectDto> deleteProject(@PathVariable int id) {
        LOGGER.debug("Delete project by its id {}", id);
        return new ResponseEntity<>(projectService.deleteProject(id), HttpStatus.NO_CONTENT);
    }

    /**
     * Update the project details by its ID
     *
     * @param projectDto {@link ProjectDto}
     * @return ProjectDto with Http status Accepted.
     */
    @PutMapping
    public ResponseEntity<ProjectDto> updateProject(@RequestBody ProjectDto projectDto) {
        LOGGER.debug("Update project details by id {}", projectDto.getId());
        return new ResponseEntity<>(projectService.updateProject(projectDto), HttpStatus.FOUND);
    }

    /**
     * Get the employee details by project id
     *
     * @param id : id of the project
     * @return EmployeeDto {@link EmployeeDto}
     */
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> retrieveEmployeesByProjectId(@PathVariable int id) {
        LOGGER.debug("Get employees by project id");
        List<EmployeeDto> employeeDto = projectService.retrieveEmployeesByProjectId(id);
        return ResponseEntity.ok(employeeDto);

    }

    /**
     * Get the all projects
     *
     * @return ProjectDto {@link ProjectDto}
     */
    @GetMapping
    public ResponseEntity<List<ProjectDto>> retrieveProjects() {
        LOGGER.debug("Get Projects");
        return new ResponseEntity<>(projectService.retrieveProjects(), HttpStatus.FOUND);
    }

    /**
     * Add project to employee
     *
     * @return EmployeeDto {@link EmployeeDto}.
     */
    @PutMapping("/{employeeId}/add_project/{projectId}")
    public ResponseEntity<ProjectDto> addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        LOGGER.debug("add project to employee");
        return new ResponseEntity<>(projectService.addProjectToEmployee(employeeId, projectId), HttpStatus.ACCEPTED);
    }

    /**
     * Get the project details by project id
     *
     * @param id : id of the project
     * @return ProjectDto {@link ProjectDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> retrieveProjectById(@PathVariable int id) {
        LOGGER.debug("Get project by id");
        ProjectDto projectDto = projectService.retrieveProjectById(id);
        return ResponseEntity.ok(projectDto);
    }

    /**
     * Remove project to employee
     *
     * @return EmployeeDto {@link EmployeeDto}.
     */
    @PutMapping("/{employeeId}/remove_project/{projectId}")
    public ResponseEntity<ProjectDto> removeProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId) {
        LOGGER.debug("remove project to employee");
        return new ResponseEntity<>(projectService.removeProjectToEmployee(employeeId, projectId), HttpStatus.ACCEPTED);
    }
}
