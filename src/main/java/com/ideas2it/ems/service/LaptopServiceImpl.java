package com.ideas2it.ems.service;

import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.exception.MyException;
import com.ideas2it.ems.mapper.LaptopMapper;
import com.ideas2it.ems.mapper.EmployeeMapper;
import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.dao.LaptopDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {
    @Autowired
    private LaptopDao laptopDao;

    private static final Logger LOGGER = LogManager.getLogger(LaptopServiceImpl.class);

    @Override
    public LaptopDto addLaptop(LaptopDto laptopDto) {
        if (laptopDao.existsByLaptopName(laptopDto.getName())) {
            LOGGER.warn("Laptop Already Found in DB {}{}", laptopDto.getName(), laptopDto.getId());
            throw new MyException("Laptop Already found : " + laptopDto.getName());
        }
        return LaptopMapper.convertDto(laptopDao.save(LaptopMapper.convertObject(laptopDto)));
    }

    @Override
    public LaptopDto deleteLaptop(int id) {
        var laptop = laptopDao.findByLaptopIdAndIsDeletedFalse(id);
        if (laptop == null) {
            LOGGER.warn("laptop not Found {}", id);
            throw new MyException("Laptop not found :" + id);
        }
        laptop.setDeleted(true);
        return LaptopMapper.convertDto(laptopDao.saveAndFlush(laptop));
    }

    @Override
    public LaptopDto updateLaptop(LaptopDto laptopDto) {
        if (laptopDao.existsByLaptopName(laptopDto.getName())) {
            LOGGER.warn("Laptop Already Found {}{}", laptopDto.getName(), laptopDto.getId());
            throw new MyException("Laptop Already found : " + laptopDto.getName());
        }
        var laptop = laptopDao.findByLaptopIdAndIsDeletedFalse(laptopDto.getId());
        if (laptop == null) {
            LOGGER.warn("Laptop not Found {}", laptopDto.getId());
            throw new MyException("Laptop not found :" + laptopDto.getId());
        }
        laptop.setLaptopName(laptopDto.getName());
        return LaptopMapper.convertDto(laptopDao.save(laptop));
    }

    @Override
    public EmployeeDto retrieveEmployeeByLaptopId(int id) {
        var laptop = laptopDao.findByLaptopIdAndIsDeletedFalse(id);
        if (laptop == null) {
            LOGGER.warn("Laptop not Found {}", id);
            throw new MyException("Laptop not Found : " + id);
        }
        if (laptop.getEmployee() == null) {
            LOGGER.warn("Employee not Found in this laptop id {}", id);
            throw new MyException("Employees not found in laptop id : " + id);
        }
        return EmployeeMapper.convertDto(laptop.getEmployee());
    }

    @Override
    public List<LaptopDto> retrieveLaptops() {
        List<LaptopDto> laptopDtos = new ArrayList<>();
        var laptops = laptopDao.findByIsDeletedFalse();
        if (laptops.isEmpty()) {
            LOGGER.warn("No Laptops Available");
            throw new MyException("No Laptops Available");
        }
        for (Laptop laptop : laptops) {
            laptopDtos.add(LaptopMapper.convertDto(laptop));
        }
        return laptopDtos;
    }

    @Override
    public LaptopDto retrieveLaptopById(int id) {
        var laptop = laptopDao.findByLaptopIdAndIsDeletedFalse(id);
        if (laptop == null) {
            LOGGER.warn("Laptop not Found {}", id);
            throw new MyException("Laptop not Found");
        }
        return LaptopMapper.convertDto(laptop);
    }
}
