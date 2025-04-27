package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;

import java.util.List;

public interface OrderServiceInterface {

    List<Order> getAllOrders();
    Order getOrderById(Integer id);
    Order updateOrder(Integer id, Order order);
    Order addOrder(Order order);
    void deleteOrder(Integer id);
    void cancelOrder(Integer id);
    List<Order> getActiveOrders();

   /*
---customer
    /addorder
cancelorder
activeorders
orders

admin
----
activeorders
orders-historiska
DELETE /api/v1/admin/removeorder- Ta bort bokning från systemet
 • DELETE /api/v1/admin/removeorders-beforedate/{date}
  vanligaste hyresperiod (antal dagar)
  genomsnittlig
kostnad per hyresorder.


    */
}
