package com.ideas2it.ems.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

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

    @Column(unique = true, nullable = false)
    private String departmentName;

    @Column
    private boolean isDeleted;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonIgnore
    Set<Employee> employees;
}
