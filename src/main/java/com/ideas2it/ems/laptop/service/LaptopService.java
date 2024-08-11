package com.ideas2it.ems.laptop.service;

import com.ideas2it.ems.laptop.dto.LaptopDto;
import com.ideas2it.ems.model.Laptop;

import java.util.List;


public interface LaptopService {
    LaptopDto addLaptop(Laptop laptop);

    LaptopDto deleteLaptop(Laptop laptop);

    LaptopDto updateLaptop(Laptop laptop);

    LaptopDto retrieveLaptopById(int id);

    List<Laptop> retrieveLaptops();
}
