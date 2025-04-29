package com.example.ahbiluthyrningssystem.controllers;


import java.security.Principal;
import java.util.List;

import com.example.ahbiluthyrningssystem.services.CarService;
import com.example.ahbiluthyrningssystem.services.CustomerServiceImp;
import com.example.ahbiluthyrningssystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;


//--------------------- Wille & Elham - class AdminController --------------
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private Principal principal;
    private CustomerServiceImp customerServiceImp;
    private CarService carService;
    private OrderService orderService;

    @Autowired
    public AdminController(CustomerServiceImp customerServiceImp, CarService carService, OrderService orderService) {
        this.customerServiceImp = customerServiceImp;
        this.carService = carService;
        this.orderService = orderService;
    }

    //  Wille & Elham
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars(Integer id) {
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    //  Wille & Elham
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerServiceImp.getAllCustomers());
    }

    //  Wille & Elham
    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerServiceImp.getCustomerById(id));
    }

    //  Wille & Elham
    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImp.addCustomer(customer));
    }

    // Elham
    @DeleteMapping("/removecustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Integer id) {
        customerServiceImp.deleteCustomerById(id);
        return ResponseEntity.ok("Customer with Id:" + id + " has been successfully deleted.");
    }

    //  Wille
    @PutMapping("/updatecar")
    public ResponseEntity<Car> updateCar() {return null;}
}
