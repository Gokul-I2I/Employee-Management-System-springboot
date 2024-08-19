package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Department;

import com.ideas2it.ems.model.Employee;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    private static final Logger LOGGER = LogManager.getLogger(DepartmentServiceImpl.class);

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        if (departmentDao.existsByDepartmentName(departmentDto.getName())) {
            LOGGER.warn("Department Already Found with this name{}", departmentDto.getName());
            throw new MyException("Department Already found : " + departmentDto.getName());
        }
        return DepartmentMapper.convertDto(departmentDao.save(DepartmentMapper.convertObject(departmentDto)));
    }

    @SneakyThrows
    @Override
    public DepartmentDto deleteDepartment(int id) {
        Department department = departmentDao.findByDepartmentIdAndIsDeletedFalse(id);
        if (department == null) {
            LOGGER.warn("Department Not Found {}", id);
            throw new NoSuchFieldException("Department not found : " + id);
        } else if (department.isDeleted()) {
            throw new MyException("Department Not Found " + id);
        }
        department.setDeleted(true);
        return DepartmentMapper.convertDto(departmentDao.saveAndFlush(department));
    }

    @SneakyThrows
    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        if (departmentDao.existsByDepartmentName(departmentDto.getName())) {
            LOGGER.warn("Department Already Found in DB {}{}", departmentDto.getName(), departmentDto.getId());
            throw new MyException("Department Already found : " + departmentDto.getName());
        }
        var department = departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentDto.getId());
        if (department == null) {
            LOGGER.warn("Department not Found {}", departmentDto.getId());
            throw new NoSuchFieldException("Department not found :" + departmentDto.getId());
        }
        department.setDepartmentName(departmentDto.getName());
        return DepartmentMapper.convertDto(departmentDao.save(department));
    }

    @SneakyThrows
    @Override
    public List<EmployeeDto> retrieveEmployeesByDepartmentId(int id) {
        var department = departmentDao.findByDepartmentIdAndIsDeletedFalse(id);
        if (department == null) {
            LOGGER.warn("No Departments Found in DB {}", id);
            throw new NoSuchFieldException("Department not Found : " + id);
        }
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        if (department.getEmployees().isEmpty()) {
            LOGGER.warn("Employees not Found department id{}", id);
            throw new NoSuchFieldException("Employees not found in department id : " + id);
        }
        for (Employee employee : department.getEmployees()) {
            if (!employee.isDeleted()) {
                employeeDtos.add(EmployeeMapper.convertDto(employee));
            }
        }
        return employeeDtos;
    }

    @SneakyThrows
    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        var departments = departmentDao.findByIsDeletedFalse();
        if (departments.isEmpty()) {
            LOGGER.warn("No Department Found");
            throw new NoSuchFieldException("No Departments Available");
        }
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.convertDto(department));
        }
        return departmentDtos;
    }

    @SneakyThrows
    @Override
    public DepartmentDto retrieveDepartmentById(int id) {
        var department = departmentDao.findByDepartmentIdAndIsDeletedFalse(id);
        if (department == null) {
            LOGGER.warn("Department not found");
            throw new NoSuchFieldException("Department not Found");
        }
        return DepartmentMapper.convertDto(department);
    }
}
