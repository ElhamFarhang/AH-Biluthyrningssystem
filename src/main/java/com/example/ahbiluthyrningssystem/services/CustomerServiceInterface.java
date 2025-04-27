package com.example.ahbiluthyrningssystem.services;


import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;

import java.util.List;

//--------------------- Elham - CustomerServiceInterface --------------
public interface CustomerServiceInterface {



    Customer updateInfo(Integer id,Customer customer);
}
