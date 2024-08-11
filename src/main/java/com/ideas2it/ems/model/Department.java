package com.ideas2it.ems.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the model of the Department details.
 *
 * @author Gokul
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentId;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean is_Deleted;
}
