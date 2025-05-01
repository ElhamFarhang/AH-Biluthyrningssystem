package com.example.ahbiluthyrningssystem.controllers;


import java.security.Principal;
import java.util.List;

import com.example.ahbiluthyrningssystem.services.CarServiceImpl;
import com.example.ahbiluthyrningssystem.services.CustomerServiceImpl;
import com.example.ahbiluthyrningssystem.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;


//--------------------- Wille & Elham - class AdminController --------------
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private CustomerServiceImpl customerServiceImpl;
    private CarServiceImpl carServiceImpl;
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    public AdminController(CustomerServiceImpl customerServiceImpl, CarServiceImpl carService, OrderServiceImpl orderService) {
        this.customerServiceImpl = customerServiceImpl;
        this.carServiceImpl = carService;
        this.orderServiceImpl = orderService;
    }

    //  Wille & Elham
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(carServiceImpl.getAvailableCars());
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
        return ResponseEntity.ok("Customer with Id:" + id + " has been successfully deleted.");
    }

    //  Wille
    @PutMapping("/updatecar")
    public ResponseEntity<Car> updateCar() {return null;}

    //Elham
    @GetMapping("/authenticated")
    public String authenticated(Principal principal) {
        return "You are logged as: " + principal.getName();
    }
}
