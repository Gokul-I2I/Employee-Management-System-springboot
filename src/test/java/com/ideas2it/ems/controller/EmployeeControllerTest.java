package com.ideas2it.ems.controller;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.service.EmployeeService;
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
public class EmployeeControllerTest {
    @Mock
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;

    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employeeDto = EmployeeDto.builder()
                .id(1)
                .name("it")
                .build();
    }

    @Test
    void testAddEmployee() {
        when(employeeService.addEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.addEmployee(employeeDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeDto, response.getBody());
        verify(employeeService, times(1)).addEmployee(employeeDto);
    }
    @Test
    void testDeleteEmployee() {
        when(employeeService.deleteEmployee(employeeDto.getId())).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.deleteEmployee(employeeDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeService, times(1)).deleteEmployee(employeeDto.getId());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeService.updateEmployee(employeeDto)).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(employeeDto);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(employeeService, times(1)).updateEmployee(employeeDto);
    }

    @Test
    void testRetrieveEmployeeById() {
        when(employeeService.retrieveEmployeeById(employeeDto.getId())).thenReturn(employeeDto);
        ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(employeeDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).retrieveEmployeeById(employeeDto.getId());
    }

    @Test
    void testRetrieveEmployees() {
        when(employeeService.retrieveEmployees()).thenReturn(List.of(employeeDto));
        ResponseEntity<List<EmployeeDto>> response = employeeController.getEmployees();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(employeeService, times(1)).retrieveEmployees();
    }
}
