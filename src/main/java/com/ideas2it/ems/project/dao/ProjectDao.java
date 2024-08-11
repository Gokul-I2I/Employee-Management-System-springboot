package com.ideas2it.ems.project.dao;

import com.ideas2it.ems.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDao extends JpaRepository<Project, Integer> {
}
