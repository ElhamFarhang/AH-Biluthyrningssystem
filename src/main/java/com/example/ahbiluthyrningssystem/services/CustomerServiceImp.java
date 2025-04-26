package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//--------------------- Elham - CustomerServiceImp --------------
@Service
public class CustomerServiceImp implements CustomerServiceInterface{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


}
