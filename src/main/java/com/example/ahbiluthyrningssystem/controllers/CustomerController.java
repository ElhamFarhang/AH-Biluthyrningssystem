package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    //  Wille
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return null;
    }

    //  Wille
    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return null;
    }

    //  Wille
    @PutMapping("/cancelorder")
    public ResponseEntity<Order> cancelOrder(@RequestBody Order order) {
        return null;
    }

    //  Wille
    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders() {
        return null;
    }

    //  Wille
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return null;
    }

    //  Wille
    @PutMapping("/updateinfo")
    public ResponseEntity<Customer> updateOrder(@RequestBody Customer customer) {
        return null;
    }
}
