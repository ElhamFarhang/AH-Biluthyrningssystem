package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

 @SpringBootTest
class OrderServiceImplTest {

     private OrderServiceImpl orderService;
     private OrderRepository orderRepository;

     @Autowired
     public void setOrderService(OrderServiceImpl orderService, OrderRepository orderRepository) {
         this.orderService = orderService;
         this.orderRepository = orderRepository;
     }



    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void setPrincipal() {
    }

    @Test
    void addOrder() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void getActiveOrdersCustomer() {
    }

    @Test
    void getOldOrdersCustomer() {
    }

    @Test
    void getActiveOrdersAdmin() {
    }

    @Test
    void getOldOrdersAdmin() {
    }

    @Test
    void deleteOrder() {
    }

    @Test
    void deleteAllOrdersBeforeDate() {
    }
}