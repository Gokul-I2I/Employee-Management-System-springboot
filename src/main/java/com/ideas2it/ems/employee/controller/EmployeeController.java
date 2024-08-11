package com.ideas2it.ems.employee.controller;


import com.ideas2it.ems.employee.dto.EmployeeDto;
import com.ideas2it.ems.employee.mapper.Mapper;
import com.ideas2it.ems.employee.service.EmployeeService;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addEmployee(Mapper.convertObject(employeeDto));
    }

    @PutMapping("/delete")
    public EmployeeDto deleteEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.deleteEmployee(Mapper.convertObject(employeeDto));
    }

    @PutMapping("/update")
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(Mapper.convertObject(employeeDto));
    }

    @GetMapping("/view_id/{id}")
    public ResponseEntity<EmployeeDto> retrieveEmployeeById(@PathVariable int id) {
        EmployeeDto employeeDto = employeeService.retrieveEmployeeById(id);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view_all")
    public List<Employee> retrieveEmployees() {
        return employeeService.retrieveEmployees();
    }
}

