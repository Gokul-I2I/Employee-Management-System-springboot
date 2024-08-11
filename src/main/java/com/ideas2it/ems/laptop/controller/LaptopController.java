package com.ideas2it.ems.laptop.controller;

import com.ideas2it.ems.laptop.dto.LaptopDto;
import com.ideas2it.ems.laptop.mapper.Mapper;
import com.ideas2it.ems.laptop.service.LaptopService;
import com.ideas2it.ems.model.Laptop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @PostMapping("/add")
    public LaptopDto addLaptop(@RequestBody LaptopDto laptopDto) {
        return laptopService.addLaptop(Mapper.convertObject(laptopDto));
    }

    @PutMapping("/delete")
    public LaptopDto deleteLaptop(@RequestBody LaptopDto laptopDto) {
        return laptopService.deleteLaptop(Mapper.convertObject(laptopDto));
    }

    @PutMapping("/update")
    public LaptopDto updateLaptop(@RequestBody LaptopDto laptopDto) {
        return laptopService.updateLaptop(Mapper.convertObject(laptopDto));
    }

    @GetMapping("/view_id/{id}")
    public ResponseEntity<LaptopDto> retrieveLaptopById(@PathVariable int id) {
        LaptopDto laptopDto = laptopService.retrieveLaptopById(id);
        if (laptopDto != null) {
            return ResponseEntity.ok(laptopDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view_all")
    public List<Laptop> retrieveLaptops() {
        return laptopService.retrieveLaptops();
    }
}
