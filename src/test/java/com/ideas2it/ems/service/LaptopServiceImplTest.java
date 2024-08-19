package com.ideas2it.ems.service;

import com.ideas2it.ems.dao.LaptopDao;
import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.model.Department;
import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LaptopServiceImplTest {
    @Mock
    private LaptopDao laptopDao;

    @InjectMocks
    private LaptopServiceImpl laptopService;

    private LaptopDto laptopDto;
    private Laptop laptop;
    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        laptop = Laptop.builder()
                .laptopId(1)
                .laptopName("Thinkpadt14")
                .isDeleted(false)
                .build();

        laptopDto = LaptopDto.builder()
                .id(1)
                .name("Thinkpadt14")
                .build();

        department = Department.builder()
                .departmentId(1)
                .departmentName("it")
                .build();

        employee = Employee.builder()
                .id(1)
                .name("gokul")
                .dateOfBirth(LocalDate.of(2000, 12, 12))
                .laptop(laptop)
                .department(department)
                .isDeleted(false)
                .build();
    }
    @Test
    void testAddLaptop() {
        when(laptopDao.existsByLaptopName(laptopDto.getName())).thenReturn(false);
        when(laptopDao.save(any(Laptop.class))).thenReturn(laptop);
        var result = laptopService.addLaptop(laptopDto);
        assertNotNull(result);
        assertEquals(result.getName(), laptopDto.getName());
    }

    @Test
    void testDeleteLaptop() {
        when(laptopDao.findByLaptopIdAndIsDeletedFalse(laptopDto.getId())).thenReturn(laptop);
        when(laptopDao.saveAndFlush(any(Laptop.class))).thenReturn(laptop);
        laptopService.deleteLaptop(laptopDto.getId());
        assertTrue(laptop.isDeleted());
    }

    @Test
    void testGetAllLaptops() {
        when(laptopDao.findByIsDeletedFalse()).thenReturn(List.of(laptop));
        List<LaptopDto> result = laptopService.retrieveLaptops();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testUpdateLaptop() {
        when(laptopDao.findByLaptopIdAndIsDeletedFalse(laptopDto.getId())).thenReturn(laptop);
        when(laptopDao.existsByLaptopName(laptop.getLaptopName())).thenReturn(false);
        laptop.setLaptopName("fb");
        when(laptopDao.save(any(Laptop.class))).thenReturn(laptop);
        var result = laptopService.updateLaptop(laptopDto);
        assertNotNull(result);
        assertEquals(laptopDto.getName(), result.getName());
    }

    @Test
    void testRetrieveLaptopById() {
        when(laptopDao.findByLaptopIdAndIsDeletedFalse(laptopDto.getId())).thenReturn(laptop);
        var laptops = laptopService.retrieveLaptopById(laptopDto.getId());
        assertEquals(laptops.getName(), laptopDto.getName());
    }
}
