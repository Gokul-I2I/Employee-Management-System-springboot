package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Laptop;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(EmployeeServiceImpl.class);

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.convertObject(employeeDto);
        Laptop laptop = new Laptop(employeeDto.getLaptopName());
        employee.setLaptop(laptop);
        return EmployeeMapper.convertDto(employeeDao.save(employee));
    }

    @Override
    public EmployeeDto deleteEmployee(int id) {
        Employee employee = employeeDao.findByIdAndIsDeletedFalse(id);
        if (employee == null) {
            LOGGER.warn("Employee not found {}", id);
            throw new MyException("employee not found : " + id);
        } else if (employee.isDeleted()) {
            LOGGER.warn("Employee Already Deleted {}", id);
            throw new MyException("Employee Already Deleted " + id);
        }
        employee.setDeleted(true);
        return EmployeeMapper.convertDto(employeeDao.saveAndFlush(employee));
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeDao.findByIdAndIsDeletedFalse(employeeDto.getId());
        if (employee == null) {
            LOGGER.warn("Employee Not Found {}", employeeDto.getId());
            throw new MyException("Employee not found :" + employeeDto.getId());
        }
        return EmployeeMapper.convertDto(employeeDao.saveAndFlush(EmployeeMapper.convertObject(employeeDto)));
    }

    @SneakyThrows
    @Override
    public EmployeeDto retrieveEmployeeById(int id) {
        var employee = employeeDao.findByIdAndIsDeletedFalse(id);
        if (employee == null) {
            LOGGER.warn("employee not Found {}", id);
            throw new NoSuchFieldException("employee not Found");
        }
        return EmployeeMapper.convertDto(employeeDao.findByIdAndIsDeletedFalse(id));
    }

    @SneakyThrows
    @Override
    public List<EmployeeDto> retrieveEmployees() {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        var employees = employeeDao.findByIsDeletedFalse();
        for (Employee employee : employees) {
            employeeDtos.add(EmployeeMapper.convertDto(employee));
        }
        if (employeeDtos.isEmpty()) {
            LOGGER.warn("Employees not found");
            throw new NoSuchFieldException("Employees not found");
        }
        return employeeDtos;
    }
}
