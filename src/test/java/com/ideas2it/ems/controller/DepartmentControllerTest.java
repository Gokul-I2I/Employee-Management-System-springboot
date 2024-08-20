package com.ideas2it.ems.controller;

import com.ideas2it.ems.dto.DepartmentDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @Mock
    DepartmentService departmentService;

    @InjectMocks
    DepartmentController departmentController;

    private DepartmentDto departmentDto;

    @BeforeEach
    void setUp() {
        departmentDto = DepartmentDto.builder()
                .id(1)
                .name("it")
                .build();
    }

    @Test
    void testAddDepartment() {
        when(departmentService.addDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.addDepartment(departmentDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());
        verify(departmentService, times(1)).addDepartment(departmentDto);
    }
    @Test
    void testDeleteDepartment() {
        when(departmentService.deleteDepartment(departmentDto.getId())).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.deleteDepartment(departmentDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departmentService, times(1)).deleteDepartment(departmentDto.getId());
    }

    @Test
    void testUpdateDepartment() {
        when(departmentService.updateDepartment(departmentDto)).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.updateDepartment(departmentDto);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(departmentService, times(1)).updateDepartment(departmentDto);
    }

    @Test
    void testRetrieveDepartmentById() {
        when(departmentService.retrieveDepartmentById(departmentDto.getId())).thenReturn(departmentDto);
        ResponseEntity<DepartmentDto> response = departmentController.getDepartmentById(departmentDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(departmentService, times(1)).retrieveDepartmentById(departmentDto.getId());
    }

    @Test
    void testRetrieveDepartments() {
        when(departmentService.retrieveDepartments()).thenReturn(List.of(departmentDto));
        ResponseEntity<List<DepartmentDto>> response = departmentController.getDepartments();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(departmentService, times(1)).retrieveDepartments();
    }
}
