package com.ideas2it.ems.utils;

import java.time.LocalDate;
import java.time.Period;

/**
 * validating various type of input
 */
public class Validator {
    /**
     * Calculate the age of the employee
     * @param dateOfBirth : employee date of birth
     * @return age of the employee
     */
    public static int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
