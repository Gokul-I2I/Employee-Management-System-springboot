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

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);

    /**
     * Create the employee based on the user request
     *
     * @param employeeDto {@link EmployeeDto}
     * @return EmployeeDto with Http status Created.
     */
    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        LOGGER.debug("Add employee details to Database {}", employeeDto.getName());
        var employee = employeeService.addEmployee(employeeDto);
        LOGGER.info("Employee added ");
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    /**
     * Delete the employee by its id
     *
     * @param id : id of the employee
     * @return employeeDto with Http status No_Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable int id) {
        LOGGER.debug("Delete employee by id{}", id);
        EmployeeDto employeeDto = employeeService.deleteEmployee(id);
        LOGGER.info("Delete employee details by id {}", id);
        return new ResponseEntity<>(employeeDto, HttpStatus.GONE);
    }

    /**
     * Update the employee details by its ID
     *
     * @param employeeDto {@link EmployeeDto}
     * @return employeeDto with Http status Found.
     */
    @PutMapping
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        LOGGER.debug("Update employee details by id{}", employeeDto.getId());
        employeeDto = employeeService.updateEmployee(employeeDto);
        if (employeeDto != null) {
            LOGGER.info("Update employee details by id {}", employeeDto.getId());
            return new ResponseEntity<>(employeeDto, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get details of the employee by its id
     *
     * @param id : id of the employee
     * @return EmployeeDto with Http status Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) {
        LOGGER.debug("Get employee by id");
        EmployeeDto employeeDto = employeeService.retrieveEmployeeById(id);
        if (employeeDto != null) {
            LOGGER.info("Get Employee by id {}", id);
            return new ResponseEntity<>(employeeDto, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get all employees details
     *
     * @return List of EmployeeDtos with Http status Found.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployees() {
        LOGGER.debug("Get all employees");
        List<EmployeeDto> employeeDtos = employeeService.retrieveEmployees();
        if (employeeDtos.isEmpty()) {
            LOGGER.info("Get All employees ");
            return ResponseEntity.ok(employeeDtos);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

