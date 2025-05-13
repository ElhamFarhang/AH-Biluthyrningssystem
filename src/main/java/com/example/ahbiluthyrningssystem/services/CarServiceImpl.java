package com.example.ahbiluthyrningssystem.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.ahbiluthyrningssystem.entities.Order;
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

    //  Theo
    @Override
    public List<Car> getAvailableCars() {
        List<Car> cars = carRepository.findAll();
        LocalDate today = LocalDate.now();

        return cars.stream()
                .filter(car -> !isCarBooked(car, today, today))
                .collect(Collectors.toList());
    }

    // Wille
    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // Wille
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

    // Wille
    @Override
    public Car updateCar(Car car) {
        checkIfCarExists(car);

        if (car.getId() == 0) {
            throw new ResourceMissingDataException("Car", "ID");
        }

        return carRepository.save(car);
    }

    // Wille
    @Override
    public Car getCarById(Integer id) {
        return carRepository.findById(id).get();
    }


    /*@Override //Theo
    public Boolean isCarBooked(Car car, LocalDate startDate, LocalDate endDate) {
       return null;
    }*/

    //Theo
    @Override
    public Boolean isCarBooked(Car car, LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            return false;
        }

        for (Order order : car.getOrders().values()) {
            if (order.isCanceled()) {
                continue;
            }

            LocalDate orderStart = order.getDateStart();
            LocalDate orderEnd = order.getDateEnd();

            boolean overlaps = !(endDate.isBefore(orderStart) || startDate.isAfter(orderEnd));
            if (overlaps) {
                return true;
            }
        }
        return false;
    }

    // Wille
    private void validateCar(Car car) {
        // TODO
    }

    // Wille
    private void checkIfCarExists(Car car) {
        if (!carRepository.existsById(car.getId())) {
            throw new ResourceNotFoundException("car", "id", car.getId());
        }
    }

}
