package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {        //Anna

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer id) {
        orderRepository.findById(id).orElseThrow(); //TODO skapa exception
        return orderRepository.findById(id).get();
    }

    @Override
    public Order updateOrder(Integer id, Order order) {
        orderRepository.findById(id).orElseThrow();  //TODO skapa exception
        return orderRepository.save(order);
    }

    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);

    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.findById(id).orElseThrow();  //TODO skapa exception
        orderRepository.deleteById(id);

    }

    // Elham - cancelOrder
    @Override
    public void cancelOrder(Integer id) {
        Optional<Order> orderToCancel = orderRepository.findById(id);
        if (!orderToCancel.isPresent())
            throw new ResourceNotFoundException("Order", "id", id);
        else {
            Order order = orderToCancel.get();
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    // Elham - getActiveOrders
    @Override
    public List<Order> getActiveOrders() {
        List<Order> orders = orderRepository.findAll();
        List<Order> activeOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isActive() == true) {
                activeOrders.add(order);
            }
        }
        return activeOrders;
    }


}
