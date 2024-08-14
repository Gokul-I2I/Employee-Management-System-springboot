package com.ideas2it.ems.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.DepartmentService;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger LOGGER = LogManager.getLogger(DepartmentController.class);

    /**
     * Create the department based on the user request
     *
     * @param departmentDto {@link DepartmentDto}
     * @return departmentDto with Http status Created.
     */
    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            LOGGER.debug("Create department {}", departmentDto.getName());
            return new ResponseEntity<>(departmentService.addDepartment(departmentDto), HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error in add Department {}", departmentDto.getName(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete the department by its id
     *
     * @param id : id of the department
     * @return departmentDto with Http status No_Content.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable int id) {
        try {
            LOGGER.debug("delete department by id {}", id);
            return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.error("Error in delete Department by id{}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the department details by its ID
     *
     * @param departmentDto {@link DepartmentDto}
     * @return departmentDto with Http status Accepted.
     */
    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto) {
        try {
            LOGGER.debug("Update Department details {}", departmentDto.getId());
            return new ResponseEntity<>(departmentService.updateDepartment(departmentDto), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.error("Error in update Department details {}", departmentDto.getName(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get all employees details from department by its ID
     *
     * @param id : id of the department
     * @return List<EmployeeDto> with Http status Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<EmployeeDto>> retrieveEmployeesByDepartmentId(@PathVariable int id) {
        try {
            LOGGER.debug("Get employees by department Id {}", id);
            return new ResponseEntity<>(departmentService.retrieveEmployeesByDepartmentId(id), HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.error("Error in get employees by Department id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the all departments and the list of employees
     *
     * @return List<Department> with httpStatus Found
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> retrieveDepartments() {
        try {
            LOGGER.debug("Get all departments in database");
            return new ResponseEntity<>(departmentService.retrieveDepartments(), HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.error("Error in get all departments ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
