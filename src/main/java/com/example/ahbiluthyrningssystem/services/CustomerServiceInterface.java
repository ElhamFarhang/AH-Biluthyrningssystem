package com.example.ahbiluthyrningssystem.services;


import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;

import java.util.List;

//--------------------- Elham - CustomerServiceInterface --------------
public interface CustomerServiceInterface {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer id);
    Customer addCustomer(Customer customer);
    Customer updateInfo(Integer id,Customer customer);
    Customer deleteCustomer(Integer id);
}
