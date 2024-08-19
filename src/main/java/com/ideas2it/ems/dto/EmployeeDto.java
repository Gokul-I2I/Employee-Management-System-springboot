package com.ideas2it.ems.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

/**
 * Data transfer object of Employee Entity
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private int id;
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z])*$", message = "Enter the Valid Name")
    private String name;
    private LocalDate dateOfBirth;
    private int age;
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z])*$", message = "Enter the Valid Name")
    private String laptopName;
    private int departmentId;
    private String departmentName;

}

