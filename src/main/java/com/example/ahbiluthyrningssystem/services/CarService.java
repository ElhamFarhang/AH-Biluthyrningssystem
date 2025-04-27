package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
