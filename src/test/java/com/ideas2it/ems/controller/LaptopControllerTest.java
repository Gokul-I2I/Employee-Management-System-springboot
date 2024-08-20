package com.ideas2it.ems.controller;

import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.service.LaptopService;
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
public class LaptopControllerTest {
    @Mock
    LaptopService laptopService;

    @InjectMocks
    LaptopController laptopController;

    private LaptopDto laptopDto;

    @BeforeEach
    void setUp() {
        laptopDto = LaptopDto.builder()
                .id(1)
                .name("it")
                .build();
    }

    @Test
    void testAddLaptop() {
        when(laptopService.addLaptop(laptopDto)).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> response = laptopController.addLaptop(laptopDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(laptopDto, response.getBody());
        verify(laptopService, times(1)).addLaptop(laptopDto);
    }
    @Test
    void testDeleteLaptop() {
        when(laptopService.deleteLaptop(laptopDto.getId())).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> response = laptopController.deleteLaptop(laptopDto.getId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(laptopService, times(1)).deleteLaptop(laptopDto.getId());
    }

    @Test
    void testUpdateLaptop() {
        when(laptopService.updateLaptop(laptopDto)).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> response = laptopController.updateLaptop(laptopDto);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        verify(laptopService, times(1)).updateLaptop(laptopDto);
    }

    @Test
    void testRetrieveLaptopById() {
        when(laptopService.retrieveLaptopById(laptopDto.getId())).thenReturn(laptopDto);
        ResponseEntity<LaptopDto> response = laptopController.getLaptopById(laptopDto.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(laptopService, times(1)).retrieveLaptopById(laptopDto.getId());
    }

    @Test
    void testRetrieveLaptops() {
        when(laptopService.retrieveLaptops()).thenReturn(List.of(laptopDto));
        ResponseEntity<List<LaptopDto>> response = laptopController.retrieveLaptops();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(laptopService, times(1)).retrieveLaptops();
    }
}
