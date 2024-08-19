package com.ideas2it.ems.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * Data Transfer Object of Department Entity
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private int id;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z])*$", message = "Enter the Valid Name")
    private String name;

}
