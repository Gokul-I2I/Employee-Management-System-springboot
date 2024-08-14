package com.ideas2it.ems.dto;

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
    private String name;
}
