package com.ideas2it.ems.service;

import com.ideas2it.ems.dao.DepartmentDao;
import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Employee;
import com.ideas2it.ems.model.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private DepartmentDao departmentDao;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private DepartmentDto departmentDto;
    private Department department;
    private Employee employee;
    private Employee employee1;

    @BeforeEach
    void setUp() {
        Laptop laptop = Laptop.builder()
                .laptopName("thinkpad")
                .build();

        department = Department.builder()
                .departmentId(1)
                .departmentName("it")
                .isDeleted(false)
                .build();

        departmentDto = DepartmentDto.builder()
                .id(1)
                .name("it")
                .build();

        employee = Employee.builder()
                .id(1)
                .name("gokul")
                .dateOfBirth(LocalDate.of(2000,12,12))
                .laptop(laptop)
                .isDeleted(false)
                .department(department)
                .build();

        employee1 = Employee.builder()
                .id(2)
                .name("kishore")
                .dateOfBirth(LocalDate.of(2000,12,12))
                .isDeleted(true)
                .laptop(laptop)
                .department(department)
                .build();
    }

    @Test
    void testAddDepartment() {
        when(departmentDao.existsByDepartmentName(departmentDto.getName())).thenReturn(false);
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        DepartmentDto createdDepartment = departmentService.addDepartment(departmentDto);
        assertNotNull(createdDepartment);
        assertEquals(departmentDto.getName(), createdDepartment.getName());
    }
    @Test
    void testAddDepartment_AlreadyPresent() {
        when(departmentDao.existsByDepartmentName(department.getDepartmentName())).thenReturn(true);
        assertThrows(MyException.class,() -> departmentService.addDepartment(departmentDto));
    }

    @Test
    void testDeleteDepartment() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentDto.getId())).thenReturn(department);
        when(departmentDao.saveAndFlush(any(Department.class))).thenReturn(department);
        DepartmentDto result = departmentService.deleteDepartment(departmentDto.getId());
        assertTrue(department.isDeleted());
    }

    @Test
    void testDeleteDepartment_InvalidId() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(4)).thenReturn(department);
        assertThrows(NullPointerException.class,() -> departmentService.deleteDepartment(4));
    }

    @Test
    void testDeleteDepartment_AlreadyDeleted() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(1)).thenReturn(department);
        assertThrows(NullPointerException.class,() -> departmentService.deleteDepartment(1));
    }

    @Test
    void testGetAllDepartments() {
        when(departmentDao.findByIsDeletedFalse()).thenReturn(List.of(department));
        List<DepartmentDto> result = departmentService.retrieveDepartments();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateDepartment() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentDto.getId())).thenReturn(department);
        when(departmentDao.existsByDepartmentName(department.getDepartmentName())).thenReturn(false);
        department.setDepartmentName("HR");
        when(departmentDao.save(any(Department.class))).thenReturn(department);
        var result = departmentService.updateDepartment(departmentDto);
        assertNotNull(result);
        assertEquals(departmentDto.getName(), result.getName());
    }
    @Test
    void testUpdateDepartment_InvalidId() {
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(5)).thenReturn(department);
        assertThrows(Exception.class,() -> departmentService.updateDepartment(departmentDto));
    }

    @Test
    void testRetrieveEmployeesByDepartmentId() {
        department.setEmployees(Set.of(employee, employee1));
        when(departmentDao.findByDepartmentIdAndIsDeletedFalse(departmentDto.getId())).thenReturn(department);
        List<EmployeeDto> employees = departmentService.retrieveEmployeesByDepartmentId(departmentDto.getId());
        assertEquals(1, employees.size());
    }
}
