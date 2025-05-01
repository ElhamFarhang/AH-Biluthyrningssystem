package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


//--------------------- Wille & Elham - class CustomerController --------------
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerService customerServiceImpl;
    private CarServiceInterface carServiceImpl;
    private OrderService orderServiceImpl;

    //  Elham & Wille
    @Autowired
    public CustomerController(CustomerService customerServiceImpl, CarServiceInterface carServiceImpl, OrderService orderServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.carServiceImpl = carServiceImpl;
        this.orderServiceImpl = orderServiceImpl;
    }

    //  Wille & Elham
    @GetMapping({"/cars", "/admin/cars"})
    public ResponseEntity<List<Car>> getAvailableCars(){
        return ResponseEntity.ok(carServiceImpl.getAvailableCars());
    }

    //  Wille & Elham
    @PostMapping("/addorder")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderServiceImpl.addOrder(order));
    }

    //  Wille & Elham
    @PutMapping("/cancelorder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Integer id) {
        orderServiceImpl.cancelOrder(id);
        return ResponseEntity.ok("Order with Id: " + id + " has been successfully cancelled.");
    }

    //  Wille & Elham
    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders() {
        return ResponseEntity.ok(orderServiceImpl.getActiveOrdersCustomer());
    }

    //  Wille & Elham
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(Principal principal) {
        orderServiceImpl.setPrincipal(principal);
        System.out.println("Name: "+principal.getName());
        return ResponseEntity.ok(orderServiceImpl.getAllOrders());
    }

    //  Wille & Elham
    @PutMapping("/updateinfo/{id}")
    public ResponseEntity<Customer> updateInfo(@PathVariable("id") Integer id, @RequestBody Customer customer, Principal principal) {
        return ResponseEntity.ok(customerServiceImpl.updateInfo(id, customer));
    }

    //Elham
    @GetMapping("/authenticated")
    public String authenticated(Principal principal) {
        return "You are logged as: " + principal.getName();
    }
}
