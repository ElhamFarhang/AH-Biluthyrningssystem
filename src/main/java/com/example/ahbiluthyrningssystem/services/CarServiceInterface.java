package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;

import java.util.List;
import java.util.Optional;

public interface CarServiceInterface {
    List<Car> getAllCars();
    List<Car> getAvailableCars();
    Car addCar(Car car);
    void deleteCar(Car car);
    Car updateCar(Car car);
    Car getCarById(int id);
   // Optional<Car> getMostRentedCarBrand();

}
