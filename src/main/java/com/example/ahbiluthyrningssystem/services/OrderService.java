package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {


    Order addOrder(Order order);
    void cancelOrder(Integer id);
    List<Order> getActiveOrdersCustomer();
    List<Order> getOldOrdersCustomer();
    List<Order> getActiveOrdersAdmin();
    List<Order> getOldOrdersAdmin();
    void deleteOrder(Integer id);
    void deleteAllOrdersBeforeDate(LocalDate date);
}
