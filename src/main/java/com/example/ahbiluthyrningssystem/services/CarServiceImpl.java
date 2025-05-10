package com.example.ahbiluthyrningssystem.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.ahbiluthyrningssystem.exceptions.ResourceMissingDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final LoggerService LOG;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, LoggerService LOG) {
        this.carRepository = carRepository;
        this.LOG = LOG;
    }

    @Override
    public List<Car> getAvailableCars() {
       
         }
         return null;

        // Simplified
//        return cars.stream().filter(c -> !c.isBooked()).toList();
    }

    //  Wille
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    
    //  Wille
    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    // Wille
    @Override
    public void deleteCar(Car car) {
        checkIfCarExists(car);
        carRepository.delete(car);
    }

    //  Wille
    @Override
    public Car updateCar(Car car) {
        checkIfCarExists(car);
        
        if (car.getId() == 0) {
            throw new ResourceMissingDataException("Car", "ID");
        }

        return carRepository.save(car);
    }

    //  Wille
    @Override
    public Car getCarById(Integer id) {
        return carRepository.findById(id).get();
    }

    @Override
    public Boolean isCarBooked(Car car, LocalDate startDate, LocalDate endDate) {
        return null;
    }

    //  Wille
    private void validateCar(Car car){

        //  TODO
    }

    //  Wille
    private void checkIfCarExists(Car car){
        if (!carRepository.existsById(car.getId())) {
            throw new ResourceNotFoundException("car", "id", car.getId());
        }
    }

}
