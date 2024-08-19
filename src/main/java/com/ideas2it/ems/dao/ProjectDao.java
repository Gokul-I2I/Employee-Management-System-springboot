package com.ideas2it.ems.dao;

import java.util.List;

import com.ideas2it.ems.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends JpaRepository<Project, Integer> {

    Project findByProjectIdAndIsDeletedFalse(int id);

    List<Project> findByIsDeletedFalse();

    boolean existsByProjectName(String projectName);
}
