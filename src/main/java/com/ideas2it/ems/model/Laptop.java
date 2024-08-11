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

    @Column(name = "name")
    private String laptopName;

    @Column(name = "is_deleted")
    private boolean is_Deleted;
}
