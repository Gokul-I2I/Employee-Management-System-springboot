package com.ideas2it.ems.dao;

import java.util.List;

import com.ideas2it.ems.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Department data from the database.
 */
@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer> {

    Department findByDepartmentIdAndIsDeletedFalse(int id);

    List<Department> findByIsDeletedFalse();
}
