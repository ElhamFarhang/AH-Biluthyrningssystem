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
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return null;
    }

    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return null;
    }

    @PutMapping("/cancelorder")
    public ResponseEntity<Order> cancelOrder(@RequestBody Order order) {
        return null;
    }

    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders() {
        return null;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return null;
    }

    @PutMapping("/updateinfo")
    public ResponseEntity<Customer> updateOrder(@RequestBody Customer customer) {
        return null;
    }
}
