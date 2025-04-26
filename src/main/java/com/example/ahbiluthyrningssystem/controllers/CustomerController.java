package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.services.CustomerServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//--------------------- Wille & Elham - NotAcceptableException --------------
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerServiceImp customerServiceImp;

    // Elham
    @Autowired
    public CustomerController(CustomerServiceImp customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

    //  Wille & Elham
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        return ResponseEntity.ok(customerServiceImp.getAllCars());
    }

//
//    @GetMapping("/custs")
//    public ResponseEntity<List<Customer>> getAvailableCustomers() {
//        return ResponseEntity.ok(customerServiceImp.getAllCust());
//    }


    //  Wille & Elham
    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(customerServiceImp.addOrder(order));
    }

    //  Wille & Elham
    @PutMapping("/cancelorder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Integer id) {
        customerServiceImp.cancelOrder(id);
        return ResponseEntity.ok("Order with ID \" + id + \" has been successfully cancelled.");
    }

    //  Wille & Elham
    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders() {
        return ResponseEntity.ok(customerServiceImp.getActiveOrders());
    }

    //  Wille & Elham
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(customerServiceImp.getAllOrders());
    }

    //  Wille & Elham
    @PutMapping("/updateinfo/{id}")
    public ResponseEntity<Customer> updateInfo(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImp.updateInfo(id, customer));
    }

}
