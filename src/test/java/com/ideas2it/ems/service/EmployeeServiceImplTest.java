package com.ideas2it.ems.service;

import com.ideas2it.ems.dao.EmployeeDao;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.ResourceNotFound;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.model.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTest {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeDto employeeDto;
    private Employee employee;
    private Employee employee1;
    private Department department;
    private  Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Laptop laptop = Laptop.builder()
                .laptopName("thinkpad")
                .isDeleted(false)
                .build();

        department = Department.builder()
                .departmentId(1)
                .departmentName("it")
                .isDeleted(false)
                .build();

        project = Project.builder()
                .projectId(1)
                .projectName("web3")
                .isDeleted(false)
                .build();

        employeeDto = EmployeeDto.builder()
                .id(1)
                .name("gokul")
                .departmentId(1)
                .laptopName("thinkpadt14")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .build();

        employee = Employee.builder()
                .id(1)
                .name("gokul")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .laptop(laptop)
                .isDeleted(false)
                .department(department)
                .projects(Set.of(project))
                .build();

        employee1 = Employee.builder()
                .id(2)
                .name("ragul")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .laptop(laptop)
                .isDeleted(true)
                .department(department)
                .projects(Set.of(project))
                .build();
    }

    @Test
    void testAddEmployee() {
        when(employeeDao.save(any(Employee.class))).thenReturn(employee);
        EmployeeDto createdEmployee = employeeService.addEmployee(employeeDto);
        assertNotNull(createdEmployee);
        assertEquals(employeeDto.getName(), createdEmployee.getName());
    }

    @Test
    void testDeleteEmployee() {
        when(employeeDao.findByIdAndIsDeletedFalse(employeeDto.getId())).thenReturn(employee);
        when(employeeDao.saveAndFlush(any(Employee.class))).thenReturn(employee);
        EmployeeDto result = employeeService.deleteEmployee(employeeDto.getId());
        assertTrue(employee.isDeleted());
    }

    @Test
    void testDeleteEmployee_InvalidId() {
        when(employeeDao.findByIdAndIsDeletedFalse(4)).thenReturn(employee);
        assertThrows(NullPointerException.class,() -> employeeService.deleteEmployee(4));
    }
    @Test
    void testDeleteEmployee_AlreadyDeleted() {
        when(employeeDao.findByIdAndIsDeletedFalse(2)).thenReturn(employee);
        assertThrows(NullPointerException.class,() -> employeeService.deleteEmployee(2));
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> employees = List.of(employee);
        when(employeeDao.findByIsDeletedFalse()).thenReturn(employees);
        List<EmployeeDto> result = employeeService.retrieveEmployees();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeDao.findByIdAndIsDeletedFalse(employeeDto.getId())).thenReturn(employee);
        when(employeeDao.saveAndFlush(any(Employee.class))).thenReturn(employee);
        var result = employeeService.updateEmployee(employeeDto);
        assertNotNull(result);
        assertEquals(employeeDto.getName(), result.getName());
    }
    @Test
    void testUpdateEmployee_InvalidId() {
        when(employeeDao.findByIdAndIsDeletedFalse(5)).thenReturn(employee);
        assertThrows(ResourceNotFound.class,() -> employeeService.updateEmployee(employeeDto));
    }

    @Test
    void testGetEmployeeById() {
        when(employeeDao.findByIdAndIsDeletedFalse(employeeDto.getId())).thenReturn(employee);
        EmployeeDto result = employeeService.retrieveEmployeeById(employeeDto.getId());
        assertNotNull(result);
        assertEquals(employeeDto.getName(), result.getName());
    }
}
