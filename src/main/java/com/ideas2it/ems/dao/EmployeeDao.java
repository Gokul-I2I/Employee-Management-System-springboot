package com.ideas2it.ems.dao;

import java.util.List;

import com.ideas2it.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing Employee data from the database.
 */
@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findByIdAndIsDeletedFalse(int id);

    List<Employee> findByIsDeletedFalse();
}
