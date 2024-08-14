package com.ideas2it.ems.dto;

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
    private String name;
    private LocalDate dateOfBirth;
    private int age;
    private String laptopName;
    private int departmentId;
    private String departmentName;
}

