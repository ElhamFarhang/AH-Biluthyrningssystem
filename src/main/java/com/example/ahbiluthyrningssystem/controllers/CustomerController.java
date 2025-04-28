package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.services.CarService;
import com.example.ahbiluthyrningssystem.services.CustomerServiceImp;
import com.example.ahbiluthyrningssystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//--------------------- Wille & Elham - class CustomerController --------------
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerServiceImp customerServiceImp;
    private CarService carService;
    private OrderService orderService;

    public CustomerController(CustomerServiceImp customerServiceImp, CarService carService, OrderService orderService) {
        this.customerServiceImp = customerServiceImp;
        this.carService = carService;
        this.orderService = orderService;
    }

    // Elham
    @Autowired


    //  Wille & Elham
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars(){
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    //  Wille & Elham
    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.addOrder(order));
    }

    //  Wille & Elham
    @PutMapping("/cancelorder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Integer id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok("Order with Id: " + id + " has been successfully cancelled.");
    }

    //  Wille & Elham
    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders() {
        return ResponseEntity.ok(orderService.getActiveOrders());
    }

    //  Wille & Elham
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    //  Wille & Elham
    @PutMapping("/updateinfo/{id}")
    public ResponseEntity<Customer> updateInfo(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImp.updateInfo(id, customer));
    }

}
