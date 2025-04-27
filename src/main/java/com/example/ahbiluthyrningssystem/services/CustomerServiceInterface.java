package com.example.ahbiluthyrningssystem.services;


import com.example.ahbiluthyrningssystem.entities.Customer;

import java.util.List;

//--------------------- Elham - CustomerServiceInterface --------------
public interface CustomerServiceInterface {

    List<Customer> getAllCustomers();
    Customer getCustomerById(Integer id);
    Customer addCustomer(Customer customer);
    Customer updateInfo(Integer id,Customer customer);
    void deleteCustomerById(Integer id);
}
