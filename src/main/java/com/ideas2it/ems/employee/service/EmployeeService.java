package com.ideas2it.ems.employee.service;

import com.ideas2it.ems.employee.dto.EmployeeDto;
import com.ideas2it.ems.model.Employee;

import java.util.List;


public interface EmployeeService {
    EmployeeDto addEmployee(Employee employee);

    EmployeeDto deleteEmployee(Employee employee);

    EmployeeDto updateEmployee(Employee employee);

    EmployeeDto retrieveEmployeeById(int id);

    List<Employee> retrieveEmployees();
}
