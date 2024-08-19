package com.ideas2it.ems.controller;

import java.util.List;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.service.LaptopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/laptops")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    private static final Logger LOGGER = LogManager.getLogger(LaptopController.class);

    /**
     * Create the Laptop based on the user request
     *
     * @param laptopDto {@link LaptopDto}
     * @return LaptopDto with Http status Created.
     */
    @PostMapping
    public ResponseEntity<LaptopDto> addLaptop(@RequestBody LaptopDto laptopDto) {
        LOGGER.debug("Laptop Created with id {}", laptopDto.getId());
        return new ResponseEntity<>(laptopService.addLaptop(laptopDto), HttpStatus.CREATED);
    }

    /**
     * Delete the laptop by its id
     *
     * @param id : id of the department
     * @return laptopDto with Http status No_Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<LaptopDto> deleteLaptop(@PathVariable int id) {
        var laptop = laptopService.deleteLaptop(id);
        LOGGER.debug("Laptop Deleted with id {}", id);
        return new ResponseEntity<>(laptop, HttpStatus.NO_CONTENT);
    }

    /**
     * Update the laptop details by its ID
     *
     * @param laptopDto {@link LaptopDto}
     * @return laptopDto with Http status Accepted.
     */
    @PutMapping
    public ResponseEntity<LaptopDto> updateLaptop(@RequestBody LaptopDto laptopDto) {
        var laptop = laptopService.updateLaptop(laptopDto);
        LOGGER.info("Update Laptop with id {}", laptop.getId());
        return new ResponseEntity<>(laptop, HttpStatus.ACCEPTED);
    }

    /**
     * Get the employee details by laptop id
     *
     * @param id : id of the laptop
     * @return EmployeeDto {@link EmployeeDto}
     */
    @GetMapping("/{id}/employee")
    public ResponseEntity<EmployeeDto> getEmployeeByLaptopId(@PathVariable int id) {
        LOGGER.debug("Get employee by laptop id {} ", id);
        var employeeDto = laptopService.retrieveEmployeeByLaptopId(id);
        LOGGER.info("Get employee by laptop id {}", id);
        return ResponseEntity.ok(employeeDto);
    }

    /**
     * Get the all laptops and the list of employees
     *
     * @return List<Laptop> with httpStatus Found
     */
    @GetMapping
    public ResponseEntity<List<LaptopDto>> retrieveLaptops() {
        LOGGER.debug("View Laptops");
        var laptops = laptopService.retrieveLaptops();
        return new ResponseEntity<>(laptops, HttpStatus.FOUND);
    }

    /**
     * Get the laptop details by its id
     *
     * @param id : id of the laptop
     * @return laptopDto {@link LaptopDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<LaptopDto> getLaptopById(@PathVariable int id) {
        LOGGER.debug("Get department by id {}", id);
        var laptopDto = laptopService.retrieveLaptopById(id);
        LOGGER.info("Retrieved department by id {}", id);
        return new ResponseEntity<>(laptopDto, HttpStatus.FOUND);
    }
}
