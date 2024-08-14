package com.ideas2it.ems.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.mapper.DepartmentMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Department;

import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
        return DepartmentMapper.convertDto(departmentDao.save(DepartmentMapper.convertObject(departmentDto)));
    }

    @Override
    public DepartmentDto deleteDepartment(int id) {
        Department department = Objects.requireNonNull(departmentDao.getReferenceById(id));
        department.setDeleted(true);
        return DepartmentMapper.convertDto(departmentDao.saveAndFlush(department));
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto) {
        return DepartmentMapper.convertDto(departmentDao.saveAndFlush(DepartmentMapper.convertObject(departmentDto)));
    }

    @Override
    public List<EmployeeDto> retrieveEmployeesByDepartmentId(int id) {
        var department = departmentDao.findByDepartmentIdAndIsDeletedFalse(id);
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        assert department != null;
        for (Employee employee : department.getEmployees()) {
            if (!employee.isDeleted()) {
                employeeDtos.add(EmployeeMapper.convertDto(employee));
            }
        }
        return employeeDtos;
    }

    @Override
    public List<DepartmentDto> retrieveDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        var departments = departmentDao.findByIsDeletedFalse();
        for (Department department : departments) {
            departmentDtos.add(DepartmentMapper.convertDto(department));
        }
        return departmentDtos;
    }

    @Override
    public DepartmentDto retrieveDepartmentById(int id) {
        return DepartmentMapper.convertDto(departmentDao.findByDepartmentIdAndIsDeletedFalse(id));
    }
}
