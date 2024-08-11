
package com.ideas2it.ems.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents the model of the Project details.
 *
 * @author Gokul
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project")
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(name = "name")
    private String projectName;

    @Column(name = "is_deleted")
    private boolean is_Deleted;
}
