package com.ideas2it.ems.service;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.mapper.LaptopMapper;
import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.dao.LaptopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private LaptopDao laptopDao;

    @Override
    public LaptopDto addLaptop(LaptopDto laptopDto) {
        return LaptopMapper.convertDto(laptopDao.save(LaptopMapper.convertObject(laptopDto)));
    }

    @Override
    public LaptopDto deleteLaptop(int id) {
        var laptop = Objects.requireNonNull(laptopDao.findById(id).orElse(null));
        laptop.setDeleted(true);
        return LaptopMapper.convertDto(laptopDao.saveAndFlush(laptop));
    }

    @Override
    public LaptopDto updateLaptop(LaptopDto laptopDto) {
        return LaptopMapper.convertDto(laptopDao.saveAndFlush(LaptopMapper.convertObject(laptopDto)));
    }

    @Override
    public EmployeeDto retrieveEmployeeByLaptopId(int id) {
        var laptop = laptopDao.findByLaptopIdAndIsDeletedFalse(id);
        return EmployeeMapper.convertDto(laptop.getEmployee());
    }

    @Override
    public List<LaptopDto> retrieveLaptops() {
        List<LaptopDto> laptopDtos = new ArrayList<>();
        var laptops = laptopDao.findByIsDeletedFalse();
        for (Laptop laptop : laptops) {
            laptopDtos.add(LaptopMapper.convertDto(laptop));
        }
        return laptopDtos;
    }

    @Override
    public LaptopDto retrieveLaptopById(int id) {
        return LaptopMapper.convertDto(laptopDao.findByLaptopIdAndIsDeletedFalse(id));
    }
}
