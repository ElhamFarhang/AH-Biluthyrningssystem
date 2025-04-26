package com.example.ahbiluthyrningssystem.controllers;


import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
  
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(){
        return null;
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomer(){
        return null;
    }
    
    @PostMapping("/addcustomer")
    public ResponseEntity<Customer> addCustomer(){
        return null;
    }

    @PutMapping("/updatecar")
    public ResponseEntity<Car> updateCar()
}
