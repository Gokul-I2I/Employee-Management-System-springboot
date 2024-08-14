package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.dto.EmployeeDto;

public interface EmployeeService {
    /**
     * Create the new employee
     *
     * @param employeeDto : new employee details
     * @return EmployeeDto
     */
    EmployeeDto addEmployee(EmployeeDto employeeDto);

    /**
     * Delete the employee by its id from database
     *
     * @param id : id of the employee
     * @return employeeDto  : {@link EmployeeDto}
     */
    EmployeeDto deleteEmployee(int id);

    /**
     * Update the employee details by its id
     *
     * @param employeeDto : employee details with changes
     * @return employeeDto : {@link EmployeeDto}
     */
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    /**
     * Get the employee by its id
     *
     * @param id : id of the employee
     * @return EmployeeDto : {@link EmployeeDto}
     */
    EmployeeDto retrieveEmployeeById(int id);

    /**
     * Get all employees from database
     *
     * @return List<EmployeeDto> : List of all employee details
     */
    List<EmployeeDto> retrieveEmployees();
}
