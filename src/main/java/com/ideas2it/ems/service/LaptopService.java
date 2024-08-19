package com.ideas2it.ems.service;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.model.Laptop;

import java.util.List;

public interface LaptopService {
    /**
     * Create the new laptop
     *
     * @param laptopDto : new laptop details
     * @return laptopDto {@link LaptopDto}
     *
     */
    LaptopDto addLaptop(LaptopDto laptopDto);

    /**
     * Delete the laptop by its id from database
     *
     * @param id : id of the laptop
     * @return LaptopDto  : {@link LaptopDto} details of the removed department
     *
     */
    LaptopDto deleteLaptop(int id);

    /**
     * Update the laptop details by its id
     *
     * @param laptopDto : laptop details with changes
     * @return LaptopDto : {@link LaptopDto}
     */
    LaptopDto updateLaptop(LaptopDto laptopDto);

    /**
     * Get the employee by laptop id
     *
     * @param id : id of the laptop
     * @return EmployeeDto : List of {@link EmployeeDto} form the laptop id
     *
     */
    EmployeeDto retrieveEmployeeByLaptopId(int id);

    /**
     * Get all laptops from database
     *
     * @return List<LaptopDto> : List of all laptop details
     *
     */
    List<LaptopDto> retrieveLaptops();

    /**
     * Get the laptop details by its id
     * @param id : id of the laptop
     * @return laptopDto {@link Laptop}
     */
    LaptopDto retrieveLaptopById(int id);

}
