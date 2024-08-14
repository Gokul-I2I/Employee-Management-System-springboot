package com.ideas2it.ems.service;

import java.util.List;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;

public interface DepartmentService {

    /**
     * Create the new department
     *
     * @param departmentDto : new department details
     * @return departmentDto : department details
     *
     */
    DepartmentDto addDepartment(DepartmentDto departmentDto);

    /**
     * Delete the department by its id from database
     *
     * @param id : id of the department
     * @return departmentDto  : details of the removed department
     *
     */
    DepartmentDto deleteDepartment(int id);

    /**
     * Update the department name by its id
     *
     * @param departmentDto : department details with changes
     * @return departmentDto : details of the department
     */
    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    /**
     * Get the employees by department id
     *
     * @param id : id of the department
     * @return EmployeeDto : List of {@link EmployeeDto} form the department by id
     *
     */
    List<EmployeeDto> retrieveEmployeesByDepartmentId(int id);

    /**
     * Get all departments from database
     *
     * @return List<Department> : List of all departments details
     *
     */
    List<DepartmentDto> retrieveDepartments();
    /**
     * Check the department is present or not
     * @param id : id of the department
     * @return DepartmentDto {@link DepartmentDto}
     *
     */
    DepartmentDto retrieveDepartmentById(int id);
}
