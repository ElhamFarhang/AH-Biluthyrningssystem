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


//--------------------- Wille & Elham - NotAcceptableException --------------
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerServiceImp customerServiceImp;
    private CarService carService;
    private OrderService orderService;

    // Elham
    @Autowired
    public CustomerController(CustomerServiceImp customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

    //  Wille & Elham
    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAvailableCars(Integer id) {
        return ResponseEntity.ok(carService.getAvailableCars());
    }

    // Elham
    @GetMapping("/admin/customers")
    public ResponseEntity<List<Customer>> getAvailableCustomers() {
        return ResponseEntity.ok(customerServiceImp.getAllCustomers());
    }

    // Elham
    @GetMapping("/admin/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerServiceImp.getCustomerById(id));
    }

    // Elham
    @PostMapping("/admin/addcustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerServiceImp.addCustomer(customer));
    }

    // Elham
    @DeleteMapping("/admin/removecustomer/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("id") Integer id) {
        customerServiceImp.deleteCustomerById(id);
        return ResponseEntity.ok("Customer with Id:" + id + " has been successfully deleted.");
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
