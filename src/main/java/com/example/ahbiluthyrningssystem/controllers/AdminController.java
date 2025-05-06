package com.example.ahbiluthyrningssystem.controllers;


import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;


//--------------------- Wille & Elham - class AdminController --------------
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private CustomerService customerServiceImpl;
    private CarServiceInterface carServiceImpl;
    private OrderService orderServiceImpl;

    //Elham
    @Autowired
    public AdminController(CustomerService customerServiceImpl, CarServiceInterface carService, OrderService orderService) {
        this.customerServiceImpl = customerServiceImpl;
        this.carServiceImpl = carService;
        this.orderServiceImpl = orderService;
    }

    //  Wille & Elham
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerServiceImpl.getAllCustomers());
    }

    //  Wille & Elham
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerServiceImpl.getCustomerById(id));
    }

    //  Wille & Elham
    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImpl.addCustomer(customer));
    }

    // Elham
    @DeleteMapping("/removecustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Integer id) {
        customerServiceImpl.deleteCustomerById(id);
        return ResponseEntity.ok("Customer with Id: " + id + " has been successfully deleted.");
    }

    //  Wille
    @GetMapping("/allcars")
    public ResponseEntity<List<Car>> getAllCars() {
        return ResponseEntity.ok(carServiceImpl.getAllCars());
    }

    //  Wille
    @PutMapping("/updatecar")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        return ResponseEntity.ok(carServiceImpl.updateCar(car));
    }

    //  Wille
    @PostMapping("/addcar")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carServiceImpl.addCar(car));
    }

    //  Wille
    @DeleteMapping("/removecar")
    public ResponseEntity<String> deleteCar(@RequestBody Car car) {
        Car foundCar = carServiceImpl.getCarById(car.getId());
        carServiceImpl.deleteCar(foundCar);
        return ResponseEntity.ok("Car with reg num: " + foundCar.getRegistrationNumber() + " has been successfully deleted.");
    }

    // Wille
    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders() {
        return ResponseEntity.ok(orderServiceImpl.getActiveOrdersAdmin());
    }

    //  Wille
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderServiceImpl.getOldOrdersAdmin());
    }

    //  Wille
    @DeleteMapping("/removeorder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        orderServiceImpl.deleteOrder(id);
        return ResponseEntity.ok(String.format("Order with Id: %s has been successfully deleted.", id));
    }

    //  Wille & Anna
    @DeleteMapping("removeorders-beforedate/{date}")
    public ResponseEntity<String> deleteOrdersBefore(@PathVariable LocalDate date) {
        orderServiceImpl.deleteAllOrdersBeforeDate(date);
        return ResponseEntity.ok(String.format("Orders before date: %s", date));
    }

    //  Wille
    @GetMapping("/statistics")
    public ResponseEntity<Integer> getStatistics() {
        //  TODO
        //  Returna som vad?...
        return ResponseEntity.ok(null);
    }





}
