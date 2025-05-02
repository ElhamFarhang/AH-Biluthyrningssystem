package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;

import java.security.Principal;
import java.util.Date;
import java.util.List;

public interface OrderService {


    //List<Order> getAllOrders();
    //Order getOrderById(Integer id);
    //Order updateOrder(Integer id, Order order);

    Order addOrder(Order order);
    void cancelOrder(Integer id);
    List<Order> getActiveOrdersCustomer();
    List<Order> getOldOrdersCustomer();
    List<Order> getActiveOrdersAdmin();
    List<Order> getOldOrdersAdmin();
    void deleteOrder(Integer id);
    void deleteAllOrdersBeforeDate(Date date);;

    void setPrincipal(Principal principal);
}
