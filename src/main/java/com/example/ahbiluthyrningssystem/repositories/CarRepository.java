package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//--------------------- Elham - CarRepository --------------
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
