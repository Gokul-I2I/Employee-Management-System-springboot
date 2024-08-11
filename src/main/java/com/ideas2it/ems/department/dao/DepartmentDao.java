package com.ideas2it.ems.department.dao;

import com.ideas2it.ems.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentDao extends JpaRepository<Department, Integer> {
}
