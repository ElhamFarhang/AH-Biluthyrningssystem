package com.example.ahbiluthyrningssystem.services;

import java.util.ArrayList;
import java.util.List;
import com.example.ahbiluthyrningssystem.exceptions.ResourceMissingDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;

@Service
public class CarServiceImpl implements CarServiceInterface {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Elham - getAvailableCars
    @Override
    public List<Car> getAvailableCars() {
        List<Car> cars = carRepository.findAll();
         List<Car> availableCars = new ArrayList<>();
         for (Car car : cars) {
         if (!car.isBooked())
             availableCars.add(car);
         }
         return availableCars;

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
        if(car.getId() != 0) {
            car.setId(0);
        }
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
    public Car getCarById(int id) {
        return carRepository.findById(id).get();
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
