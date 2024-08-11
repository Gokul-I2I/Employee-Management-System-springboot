package com.ideas2it.ems.employee.dao;

import com.ideas2it.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {
}
