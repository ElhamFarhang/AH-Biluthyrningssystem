package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private final OrderRepository orderRepo;
    private final CarRepository carRepo;

    @Autowired
    public StatisticsService(OrderRepository orderRepo, CarRepository carRepo){
        this.carRepo = carRepo;
        this.orderRepo = orderRepo;
    }

    //Wille
    public Map<String, Integer> mostRentedMake(LocalDate start, LocalDate End){
        Map<String, Integer> mostRented = new HashMap<>();
        List<Order> orders = orderRepo.findAll();
        String make = "";
        int times = 0;
        Map<String, List<Order>> group = orders.stream().collect(Collectors.groupingBy(o -> o.getCar().getMake()));
        mostRented.put(make, times);
        return mostRented;
    }



/*  //Theo
    public List<Map<String, Integer>> timesCarRented() {
        List<Map<String, Integer>>
        timesRented.put("registrationNumber", null);
        return timesRented;
    }
*/

    //Elham
    public int mostCommonPeriodInDays() {
        int days = 0;
        return days;
    }


    //Elham
    public double averageOrderCost() {
        double cost = 0;
        return cost;
    }



/*  // Anna
    public List<Map<String, Integer>> totalIncomeCar() {
        List<Map<String, Integer>>
        timesRented.put("registrationNumber", null);
        return timesRented;
    }
*/

    //Anna
    public double totalIncomePeriod(LocalDate start, LocalDate End) {
        double cost = 0;
        return cost;
    }



}
