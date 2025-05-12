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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final OrderRepository orderRepo;
    private final CarRepository carRepo;
    private final LoggerService LOG;

    @Autowired
    public StatisticsServiceImpl(LoggerService LOG,OrderRepository orderRepo, CarRepository carRepo){
        this.carRepo = carRepo;
        this.orderRepo = orderRepo;
        this.LOG = LOG;
    }

    public Stats getStats(LocalDate start, LocalDate End) {
        Stats stats = new Stats();
        stats.setMostRentedMake(mostRentedMake(start, End));
        stats.setTimesCarRented(timesCarRented());
        stats.setMostCommonPeriodInDays(mostCommonRentalPeriodInDays());
        stats.setAverageOrderCost(calculateAverageOrderCost());
        stats.setTotalIncomeEveryCar(totalIncomeEveryCar());
        stats.setTotalIncomePeriod(totalIncomePeriod(start, End));
        LOG.logInfo("retrieved statistics");
        return stats;
    }

    //  Wille
    //  Borde nog vara Map.Entry om vi bara ska ta ut ett märke tbh
    public Map<String, Integer> mostRentedMake(LocalDate start, LocalDate end){
        List<Order> orders = orderRepo.findAll();
        Map<String, Integer> group = orders.stream().filter(o -> !o.getDateStart().isBefore(start) && !o.getDateEnd().isAfter(end))
            .collect(Collectors.groupingBy(o -> o.getCar().getMake(), Collectors.summingInt(o -> 1)));

        Optional<Entry<String, Integer>> entry = group.entrySet().stream().collect(Collectors.maxBy(Map.Entry.comparingByValue()));
        
        Map<String, Integer> mostRented = new HashMap<>();
        if(entry.isPresent())
            mostRented.put(entry.get().getKey(), entry.get().getValue());

        return mostRented;
    }



  //Theo
  public Map<String, Integer> timesCarRented() {
      Map<String, Integer> timesRented = new HashMap<>();

      List<Order> allOrders = orderRepo.findAll();
      for (Order order : allOrders) {
          String regNumber = order.getCar().getRegistrationNumber();
          timesRented.put(regNumber, timesRented.getOrDefault(regNumber, 0) + 1);
      }

      return timesRented;
  }


    // Elham - mostCommonRentalPeriodInDays
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

    // Elham - calculateAverageOrderCost
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
    public Map<String, Double> totalIncomeEveryCar() {
        List<Car> cars = carRepo.findAll();
        Map<String, Double> totalIncomeEveryCarMap = new HashMap<>();
        Double totalIncomeThisCar;
        for (Car car : cars) {
            totalIncomeThisCar = 0.0;
            List<Order> orders = orderRepo.findByCarRegistrationNumber(car.getRegistrationNumber());
            for (Order order : orders) {
                totalIncomeThisCar += order.getTotalCost();
            }
            totalIncomeEveryCarMap.put(car.getRegistrationNumber(), totalIncomeThisCar);
        }
        return totalIncomeEveryCarMap;
    }

    //Anna - Baseras på att kunden betalar vid återlämnandet av bilen.
    public Double totalIncomePeriod(LocalDate start, LocalDate End) {
        List<Order> orders = orderRepo.findByDateEndBetween(start, End);
        Double totalIncomeThisPeriod = 0.0;
        for (Order order : orders) {
            totalIncomeThisPeriod += order.getTotalCost();
        }
        return totalIncomeThisPeriod;
    }


}
