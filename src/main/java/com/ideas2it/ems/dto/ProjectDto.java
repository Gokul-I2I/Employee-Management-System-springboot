package com.ideas2it.ems.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 * Project Data Transfer Object of Project Entity
 */
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private int id;
    @Pattern(regexp = "^[A-Za-z]+( [A-Za-z])*$", message = "Enter the Valid Name")
    private String name;
}

