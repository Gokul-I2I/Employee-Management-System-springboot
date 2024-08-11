package com.ideas2it.ems.department.service;

import com.ideas2it.ems.department.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

import java.util.List;

public interface DepartmentService {

    DepartmentDto addDepartment(Department department);

    DepartmentDto deleteDepartment(Department department);

    DepartmentDto updateDepartment(Department department);

    DepartmentDto retrieveDepartmentById(int id);

    List<Department> retrieveDepartments();

}
