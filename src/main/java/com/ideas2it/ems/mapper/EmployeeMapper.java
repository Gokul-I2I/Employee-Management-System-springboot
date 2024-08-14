package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.utils.Validator;

/**
 * Object to Data Transfer Object and Dto to Object Conversion
 */
public class EmployeeMapper {

    /**
     * Convert the Employee entity to the EmployeeDto
     * @param employee {@link Employee}
     * @return EmployeeDto {@link EmployeeDto}
     */
    public static EmployeeDto convertDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .dateOfBirth(employee.getDateOfBirth())
                .age(Validator.calculateAge(employee.getDateOfBirth()))
                .departmentId(employee.getDepartment().getDepartmentId())
                .departmentName(employee.getDepartment().getDepartmentName())
                .laptopName(employee.getLaptop().getLaptopName())
                .build();
    }

    /**
     * Convert the EmployeeDto to the Employee Entity
     * @param employeeDto {@link EmployeeDto}
     * @return Employee {@link Employee}
     */
    public static Employee convertObject(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .name(employeeDto.getName())
                .dateOfBirth(employeeDto.getDateOfBirth())
                .build();
    }
}
