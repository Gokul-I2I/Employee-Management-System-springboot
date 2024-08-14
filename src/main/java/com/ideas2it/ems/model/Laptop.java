package com.ideas2it.ems.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the model of the Laptop details.
 *
 * @author Gokul
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "laptop")
@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int laptopId;

    @Column(nullable = false, unique = true)
    private String laptopName;

    @Column
    private boolean isDeleted;

    @OneToOne(mappedBy = "laptop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Employee employee;

    public Laptop(String laptopName) {
        this.laptopName = laptopName;
    }
}
