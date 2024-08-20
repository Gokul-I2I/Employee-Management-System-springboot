package com.ideas2it.ems.controller;

import java.util.List;

import jakarta.validation.Valid;
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
    public ResponseEntity<DepartmentDto> addDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        LOGGER.debug("Create department {}", departmentDto.getName());
        return new ResponseEntity<>(departmentService.addDepartment(departmentDto), HttpStatus.CREATED);
    }

    /**
     * Delete the department by its id
     *
     * @param id : id of the department
     * @return departmentDto with Http status No_Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable int id) {
        LOGGER.debug("delete department by id {}", id);
        return new ResponseEntity<>(departmentService.deleteDepartment(id), HttpStatus.NO_CONTENT);
    }

    /**
     * Update the department details by its ID
     *
     * @param departmentDto {@link DepartmentDto}
     * @return departmentDto with Http status Accepted.
     */
    @PutMapping
    public ResponseEntity<DepartmentDto> updateDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        LOGGER.debug("Update Department details {}", departmentDto.getId());
        return new ResponseEntity<>(departmentService.updateDepartment(departmentDto), HttpStatus.ACCEPTED);
    }

    /**
     * Get all employees details from department by its ID
     *
     * @param id : id of the department
     * @return List<EmployeeDto> with Http status Found.
     */
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDepartmentId(@PathVariable int id) {
        LOGGER.debug("Get employees by department Id {}", id);
        return new ResponseEntity<>(departmentService.retrieveEmployeesByDepartmentId(id), HttpStatus.OK);
    }

    /**
     * Get the all departments and the list of employees
     *
     * @return List<Department> with httpStatus Found
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments() {
        LOGGER.debug("Get all departments in database");
        var departmentDto = departmentService.retrieveDepartments();
        LOGGER.info("Get all departments in database");
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    /**
     * Get the details of department
     *
     * @return DepartmentDto with httpStatus Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable int id) {
        LOGGER.debug("Get department by id {}", id);
        var departmentDto = departmentService.retrieveDepartmentById(id);
        LOGGER.info("Retrieved department by id {}", id);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
