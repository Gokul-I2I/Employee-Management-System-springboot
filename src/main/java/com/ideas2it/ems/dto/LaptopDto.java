package com.ideas2it.ems.dto;


import lombok.*;

/**
 * Data Transfer Object of Laptop Entity
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopDto {
    private int id;
    private String name;
}