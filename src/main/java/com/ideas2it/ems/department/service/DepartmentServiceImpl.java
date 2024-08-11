package com.ideas2it.ems.department.service;

import com.ideas2it.ems.department.dao.DepartmentDao;
import com.ideas2it.ems.department.dto.DepartmentDto;
import com.ideas2it.ems.department.mapper.Mapper;
import com.ideas2it.ems.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public DepartmentDto addDepartment(Department department) {
        return Mapper.convertDto(departmentDao.save(department));
    }

    public DepartmentDto deleteDepartment(Department department) {
        return Mapper.convertDto(departmentDao.saveAndFlush(department));
    }

    public DepartmentDto updateDepartment(Department department) {
        return Mapper.convertDto(departmentDao.saveAndFlush(department));
    }

    public DepartmentDto retrieveDepartmentById(int id) {
        return Mapper.convertDto(Objects.requireNonNull(departmentDao.findById(id).orElse(null)));
    }

    public List<Department> retrieveDepartments() {
        return departmentDao.findAll();
    }
}
