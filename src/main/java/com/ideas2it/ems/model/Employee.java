package com.ideas2it.ems.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the model of the Employee details.
 *
 * @author Gokul
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    @Column(name = "name")
    private String employeeName;
    @Column(name = "is_deleted")
    private boolean is_Deleted;
}