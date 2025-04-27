package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

public class CarService implements CarServiceInterface {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Elham - getAvailableCars
    @Override
    public List<Car> getAvailableCars() {
        List<Car> cars = carRepository.findAll();
        List<Car> availableCars = new ArrayList<>();
       for (Car car : cars) {
           if (car.isBooked() == false)
                availableCars.add(car);
        }
        return availableCars;
    }
}
