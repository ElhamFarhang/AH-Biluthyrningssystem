package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.entities.Stats;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public Stats getStats(LocalDate start, LocalDate End) {
        Stats stats = new Stats();
/*        stats.setMostRentedMake(mostRentedMake(start, End));
        stats.setTimesCarRented(timesCarRented());
      */  stats.setMostCommonPeriodInDays(mostCommonRentalPeriodInDays());
        stats.setAverageOrderCost(calculateAverageOrderCost());
        stats.setTotalIncomeEveryCar(totalIncomeEveryCar());
        stats.setTotalIncomePeriod(totalIncomePeriod(start, End));
        return stats;
    }
/*

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



  //Theo
    public Map<String, Integer> timesCarRented() {
        Map<String, Integer> timesRented = new HashMap<>();
        timesRented.put("registrationNumber", null);
        return timesRented;
    }
*/
    //Elham - Flyttat till OrderServiceImpl
    public int mostCommonRentalPeriodInDays() {
        int numberOfDays = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        List<Order> allOrders = orderRepo.findAll();
        if (allOrders.isEmpty()) {
            return 0;
        }
        for (Order order : allOrders) {
            numberOfDays = (int) ChronoUnit.DAYS.between(order.getDateStart(), order.getDateEnd());
            frequencyMap.put(numberOfDays, frequencyMap.getOrDefault(numberOfDays, 0) + 1);
        }
        int mostCommonRentalPeriod=0;
        int highestCount = 0;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > highestCount) {
                highestCount = entry.getValue();
                mostCommonRentalPeriod = entry.getKey();
            }
        }
        return mostCommonRentalPeriod;
    }

    //Elham - Flyttat till OrderServiceImpl
    public double calculateAverageOrderCost() {
        List<Order> allOrders = orderRepo.findAll();
        double sum = 0;
        if (allOrders.isEmpty()) {
            return 0.0;
        }
        for (Order order : allOrders) {
            sum = sum + order.getTotalCost();
        }
        return (sum / allOrders.size());
    }



  // Anna
    //Total intäkt per bil
    public Map<String, Integer> totalIncomeEveryCar() {
        List<Car> cars = carRepo.findAll();
        Map<String, Integer> totalIncomeEveryCarMap = new HashMap<>();
        int totalIncomeThisCar;
        for (Car car : cars) {
            totalIncomeThisCar = 0;
            List<Order> orders = orderRepo.findByCarRegistrationNumber(car.getRegistrationNumber());
            for (Order order : orders) {
                totalIncomeThisCar += order.getTotalCost();
            }
            totalIncomeEveryCarMap.put(car.getRegistrationNumber(), totalIncomeThisCar);
        }
        return totalIncomeEveryCarMap;
    }

    //Anna - Baseras på att kunden betalar vid återlämnandet av bilen.
    //Total intäkt under en viss tidsperiod.  --- behöver start/slut-datum
    public int totalIncomePeriod(LocalDate start, LocalDate End) {
        List<Order> orders = orderRepo.findByDateEndBetween(start, End);
        int totalIncomeThisPeriod = 0;
        for (Order order : orders) {
            totalIncomeThisPeriod += order.getTotalCost();
        }
        return totalIncomeThisPeriod;
    }


}
