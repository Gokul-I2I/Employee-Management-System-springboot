package com.ideas2it.ems.mapper;

import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.model.Laptop;

/**
 * Object to Data Transfer Object and Dto to Object Conversion
 */
public class LaptopMapper {

    /**
     * Convert the Laptop to the LaptopDto
     * @param laptop {@link Laptop}
     * @return LaptopDto {@link LaptopDto}
     */
    public static LaptopDto convertDto(Laptop laptop) {
        return LaptopDto.builder()
                .id(laptop.getLaptopId())
                .name(laptop.getLaptopName())
                .build();
    }

    /**
     * Convert the Laptop to the LaptopDto
     * @param laptopDto {@link LaptopDto}
     * @return Laptop {@link Laptop}
     */
    public static Laptop convertObject(LaptopDto laptopDto) {
        return Laptop.builder()
                .laptopId(laptopDto.getId())
                .laptopName(laptopDto.getName())
                .build();
    }
}
