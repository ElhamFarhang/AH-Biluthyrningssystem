package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;

import java.util.Date;
import java.util.List;

public interface OrderServiceInterface {

    List<Order> getAllOrders();
    Order getOrderById(Integer id);
    Order updateOrder(Integer id, Order order);
    Order addOrder(Order order);
    void deleteOrder(Integer id);
    void deleteAllOrdersBeforeDate(Date date);
    void cancelOrder(Integer id);
    List<Order> getActiveOrders();
    List<Order> getOldOrders(Integer customerId);

}
