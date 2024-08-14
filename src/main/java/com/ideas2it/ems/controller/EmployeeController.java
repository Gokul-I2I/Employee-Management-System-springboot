package com.ideas2it.ems.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.EmployeeService;
import com.ideas2it.ems.service.LaptopService;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LaptopService laptopService;
    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

    /**
     * Create the employee based on the user request
     *
     * @param employeeDto {@link EmployeeDto}
     * @return EmployeeDto with Http status Created.
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            employeeDto = employeeService.addEmployee(employeeDto);
            LOGGER.debug("Add employee details to Database {}", employeeDto.getId());
            return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.warn("Error in employee created {}", employeeDto.getName(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete the employee by its id
     *
     * @param id : id of the employee
     * @return employeeDto with Http status No_Content.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable int id) {
        try {
            LOGGER.debug("Delete employee by id{}", id);
            return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.warn("Error in employee delete {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the employee details by its ID
     *
     * @param employeeDto {@link EmployeeDto}
     * @return employeeDto with Http status Found.
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            LOGGER.debug("Update employee details by id{}", employeeDto.getId());
            return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.warn("Error in update employee {}", employeeDto.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get details of the employee by its id
     *
     * @param id : id of the employee
     * @return EmployeeDto with Http status Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> retrieveEmployeeById(@PathVariable int id) {
        var employeeDto = employeeService.retrieveEmployeeById(id);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get all employees details
     *
     * @return List<Employee> with Http status Found.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> retrieveEmployees() {
        try {
            LOGGER.debug("Get all employees");
            return new ResponseEntity<>(employeeService.retrieveEmployees(), HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.warn("Error in get all employees", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

