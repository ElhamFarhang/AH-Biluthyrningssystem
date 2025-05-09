package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


//--------------------- Wille & Elham - class CustomerController --------------
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private final CustomerService customerServiceImpl;
    private final CarService carServiceImpl;
    private final OrderService orderServiceImpl;

    //  Elham
    @Autowired
    public CustomerController(CustomerService customerServiceImpl, CarService carServiceImpl, OrderService orderServiceImpl) {
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
    public ResponseEntity<Order> addOrder(@RequestBody Order order, Principal principal) {
        orderServiceImpl.setPrincipal(principal);
        return ResponseEntity.ok(orderServiceImpl.addOrder(order));
    }

    //  Wille & Elham
    @PutMapping("/cancelorder/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable("id") Integer id, Principal principal) {
        orderServiceImpl.setPrincipal(principal);
        orderServiceImpl.cancelOrder(id);
        return ResponseEntity.ok("Order with Id: " + id + " has been successfully cancelled.");
    }

    //  Wille & Elham
    @GetMapping("/activeorders")
    public ResponseEntity<List<Order>> getActiveOrders(Principal principal) {
        orderServiceImpl.setPrincipal(principal);
        return ResponseEntity.ok(orderServiceImpl.getActiveOrdersCustomer());
    }

    //  Wille & Elham
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(Principal principal) {
        orderServiceImpl.setPrincipal(principal);
        return ResponseEntity.ok(orderServiceImpl.getOldOrdersCustomer());
    }

    //  Wille & Elham
    @PutMapping("/updateinfo")
    public ResponseEntity<Customer> updateInfo(@RequestBody Customer customer, Principal principal) {
        System.out.println("Received customer for update: " + customer);
        return ResponseEntity.ok(customerServiceImpl.updateInfo(customer, principal));
    }
}
