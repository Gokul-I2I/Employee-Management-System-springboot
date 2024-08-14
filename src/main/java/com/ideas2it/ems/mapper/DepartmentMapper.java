package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

/**
 * Object to Data Transfer Object and Dto to Object Conversion
 */
public class DepartmentMapper {

    /**
     * Convert the department to the departmentDto
     * @param department {@link Department}
     * @return DepartmentDto {@link DepartmentDto}
     */
    public static DepartmentDto convertDto(Department department) {

        return DepartmentDto.builder()
                .id(department.getDepartmentId())
                .name(department.getDepartmentName())
                .build();
    }

    /**
     * Convert the departmentDto to the department
     * @param departmentDto {@link DepartmentDto}
     * @return Department {@link Department}
     */
    public static Department convertObject(DepartmentDto departmentDto) {

        return Department.builder()
                .departmentId(departmentDto.getId())
                .departmentName(departmentDto.getName())
                .build();
    }
}
