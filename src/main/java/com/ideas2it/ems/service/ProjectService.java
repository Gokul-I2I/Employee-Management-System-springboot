package com.ideas2it.ems.service;

import com.ideas2it.ems.dto.EmployeeDto;

import com.ideas2it.ems.dto.ProjectDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ProjectService {

     /**
      * Create the new Project
      *
      * @param projectDto : new laptop details
      * @return ProjectDto {@link ProjectDto}
      *
      */
     ProjectDto addProject(ProjectDto projectDto);

     /**
      * Delete the project by its id from database
      *
      * @param id : id of the project
      * @return projectDto  : {@link ProjectDto} details of the removed department
      *
      */
     ProjectDto deleteProject(int id);

     /**
      * Update the project details by its id
      *
      * @param projectDto : project details with changes
      * @return ProjectDto : {@link ProjectDto}
      */
     ProjectDto updateProject(ProjectDto projectDto);

     /**
      * Get the employees by project id
      *
      * @param id : id of the project
      * @return EmployeeDto : List of {@link EmployeeDto} form the project id
      *
      */
     List<EmployeeDto> retrieveEmployeesByProjectId(int id);

     /**
      * Get all project from database
      *
      * @return List<projectDto> : List of all project details
      *
      */
     List<ProjectDto> retrieveProjects();

     /**
      * Get the project details by its id
      * @param id : id of the project
      * @return projectDto {@link ProjectDto}
      */
     ProjectDto retrieveProjectById(int id);

     /**
      * Add project to employee
      *
      * @return ProjectDto {@link ProjectDto}.
      */
     ProjectDto addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId);

     /**
      * Remove project to employee
      *
      * @return ProjectDto {@link ProjectDto}.
      */
     ProjectDto removeProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId);

}
