package com.ideas2it.ems.employee.service;

import com.ideas2it.ems.employee.dao.EmployeeDao;
import com.ideas2it.ems.employee.dto.EmployeeDto;
import com.ideas2it.ems.employee.mapper.Mapper;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public EmployeeDto addEmployee(Employee employee) {
        return Mapper.convertDto(employeeDao.save(employee));
    }

    public EmployeeDto deleteEmployee(Employee employee) {
        return Mapper.convertDto(employeeDao.saveAndFlush(employee));
    }

    public EmployeeDto updateEmployee(Employee employee) {
        return Mapper.convertDto(employeeDao.saveAndFlush(employee));
    }

    public EmployeeDto retrieveEmployeeById(int id) {
        return Mapper.convertDto(Objects.requireNonNull(employeeDao.findById(id).orElse(null)));
    }

    public List<Employee> retrieveEmployees() {
        return employeeDao.findAll();
    }
}
