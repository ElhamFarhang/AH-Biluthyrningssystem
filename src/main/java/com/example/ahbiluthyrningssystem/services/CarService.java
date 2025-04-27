package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;

import java.util.List;

public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCars() {
            return carRepository.findAll();
    }
}
