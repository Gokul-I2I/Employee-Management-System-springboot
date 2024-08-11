package com.ideas2it.ems.laptop.dao;

import com.ideas2it.ems.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopDao extends JpaRepository<Laptop, Integer> {
}
