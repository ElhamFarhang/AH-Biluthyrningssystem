package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarServiceInterface {
    List<Car> getAvailableCars();
}
