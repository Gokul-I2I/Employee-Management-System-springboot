package com.ideas2it.ems.laptop.mapper;

import com.ideas2it.ems.model.Laptop;
import com.ideas2it.ems.laptop.dto.LaptopDto;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper {
    @Autowired
    public static LaptopDto convertDto(Laptop laptop) {

        return LaptopDto.builder()
                .id(laptop.getLaptopId())
                .name(laptop.getLaptopName())
                .build();
    }

    public static Laptop convertObject(LaptopDto laptopDto) {

        return Laptop.builder()
                .laptopId(laptopDto.getId())
                .laptopName(laptopDto.getName())
                .build();

    }
}
