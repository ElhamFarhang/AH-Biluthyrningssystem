package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    List<Car> getAvailableCars();
    Car addCar(Car car);
    void deleteCar(Car car);
    Car updateCar(Car car);
    Car getCarById(Integer id);
    Boolean isCarBooked(Car car, LocalDate startDate, LocalDate endDate);

}
