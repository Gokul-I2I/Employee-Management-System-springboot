package com.ideas2it.ems.controller;

import com.ideas2it.ems.dto.EmployeeDto;
import com.ideas2it.ems.dto.LaptopDto;
import com.ideas2it.ems.service.LaptopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        try {
            LOGGER.debug("Laptop Created with id {}", laptopDto.getId());
            return new ResponseEntity<>(laptopService.addLaptop(laptopDto), HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.warn("Error in laptop created {}",laptopDto.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete the laptop by its id
     *
     * @param id : id of the department
     * @return laptopDto with Http status No_Content.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LaptopDto> deleteLaptop(@PathVariable int id) {
        try {
            var laptop = laptopService.deleteLaptop(id);
            LOGGER.debug("Laptop Deleted with id {}", id);
            return new ResponseEntity<>(laptop, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LOGGER.warn(" Error in Laptop Deleted with id {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the laptop details by its ID
     *
     * @param laptopDto {@link LaptopDto}
     * @return laptopDto with Http status Accepted.
     */
    @PutMapping
    public ResponseEntity<LaptopDto> updateLaptop(@RequestBody LaptopDto laptopDto) {
        try {
            var laptop = laptopService.updateLaptop(laptopDto);
            LOGGER.debug("Update Laptop with id {}", laptop.getId());
            return new ResponseEntity<>(laptop, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.warn(" Error in update Laptop with id {}", laptopDto.getId(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the employee details by laptop id
     *
     * @param id : id of the laptop
     * @return EmployeeDto {@link EmployeeDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> retrieveEmployeeByLaptopId(@PathVariable int id) {
        var employeeDto = laptopService.retrieveEmployeeByLaptopId(id);
        if (employeeDto != null) {
            return ResponseEntity.ok(employeeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Get the all laptops and the list of employees
     *
     * @return List<Laptop> with httpStatus Found
     */
    @GetMapping
    public ResponseEntity<List<LaptopDto>> retrieveLaptops() {
        try {
            var laptops = laptopService.retrieveLaptops();
            LOGGER.debug("View Laptops");
            return new ResponseEntity<>(laptops, HttpStatus.FOUND);
        } catch (Exception e) {
            LOGGER.warn(" Error in view all laptops",e );
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
