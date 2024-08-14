package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private LaptopService laptopService;

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        var employee = EmployeeMapper.convertObject(employeeDto);
        var department = DepartmentMapper.convertObject(departmentService.retrieveDepartmentById(employeeDto.getDepartmentId()));
        var laptop = new Laptop(employeeDto.getLaptopName());
        employee.setDepartment(department);
        employee.setLaptop(laptop);
        return EmployeeMapper.convertDto(employeeDao.save(employee));
    }

    @Override
    public EmployeeDto deleteEmployee(int id) {
        var employee = Objects.requireNonNull(employeeDao.findById(id).orElse(null));
        employee.setDeleted(true);
        return EmployeeMapper.convertDto(employeeDao.saveAndFlush(employee));
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return EmployeeMapper.convertDto(employeeDao.saveAndFlush(EmployeeMapper.convertObject(employeeDto)));
    }

    @Override
    public EmployeeDto retrieveEmployeeById(int id) {
        return EmployeeMapper.convertDto(employeeDao.findByIdAndIsDeletedFalse(id));
    }

    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        var employees = employeeDao.findByIsDeletedFalse();
        for (Employee employee : employees) {
            employeeDtos.add(EmployeeMapper.convertDto(employee));
        }
        return employeeDtos;
    }
}
