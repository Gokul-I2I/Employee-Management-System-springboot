package com.ideas2it.ems.employee.mapper;

import com.ideas2it.ems.employee.dto.EmployeeDto;
import com.ideas2it.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {@Autowired
public static EmployeeDto convertDto(Employee employee) {

    return EmployeeDto.builder()
            .id(employee.getEmployeeId())
            .name(employee.getEmployeeName())
            .build();
}

    public static Employee convertObject(EmployeeDto employeeDto) {

        return Employee.builder()
                .employeeId(employeeDto.getId())
                .employeeName(employeeDto.getName())
                .build();

    }
}
