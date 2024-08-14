package com.ideas2it.ems.dto;

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
    private String name;
}

