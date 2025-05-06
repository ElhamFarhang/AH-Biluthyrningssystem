package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticService {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;


    @Autowired
    public StatisticService(CarRepository carRepository, CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public String showStats(){
       String text = null;
//        mest hyrda bilmärke under en viss period.
//        Antal gånger varje bil hyrts ut,
//        vanligaste hyresperiod (antal dagar)
//        genomsnittlig kostnad per hyresorder.
//        Total intäkt per bil.
//        Total intäkt under en viss tidsperiod.
//        return ResponseEntity.ok(text);
        return text;
    }





}
