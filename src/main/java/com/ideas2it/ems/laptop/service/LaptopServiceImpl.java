package com.ideas2it.ems.laptop.service;

import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.laptop.dao.LaptopDao;
import com.ideas2it.ems.laptop.dto.LaptopDto;
import com.ideas2it.ems.laptop.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private LaptopDao laptopDao;

    @Override
    public LaptopDto addLaptop(Laptop laptop) {
        return Mapper.convertDto(laptopDao.save(laptop));
    }

    public LaptopDto deleteLaptop(Laptop laptop) {
        return Mapper.convertDto(laptopDao.saveAndFlush(laptop));
    }

    public LaptopDto updateLaptop(Laptop laptop) {
        return Mapper.convertDto(laptopDao.saveAndFlush(laptop));
    }

    public LaptopDto retrieveLaptopById(int id) {
        return Mapper.convertDto(Objects.requireNonNull(laptopDao.findById(id).orElse(null)));
    }

    public List<Laptop> retrieveLaptops() {
        return laptopDao.findAll();
    }
}
