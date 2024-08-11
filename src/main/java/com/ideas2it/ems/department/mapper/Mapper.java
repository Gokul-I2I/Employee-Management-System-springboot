package com.ideas2it.ems.department.mapper;

import com.ideas2it.ems.department.dto.DepartmentDto;
import com.ideas2it.ems.model.Department;

public class Mapper {
    public static DepartmentDto convertDto(Department department) {

        return DepartmentDto.builder()
                .id(department.getDepartmentId())
                .name(department.getDepartmentName())
                .build();
    }
    public static Department convertObject(DepartmentDto departmentDto) {

        return Department.builder()
                .departmentId(departmentDto.getId())
                .departmentName(departmentDto.getName())
                .build();

    }
}
