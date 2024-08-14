package com.ideas2it.ems.dao;

import java.util.List;

import com.ideas2it.ems.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopDao extends JpaRepository<Laptop, Integer> {

    Laptop findByLaptopIdAndIsDeletedFalse(int id);

    List<Laptop> findByIsDeletedFalse();

}
