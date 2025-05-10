package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepositoryMock;
    @Mock
    private CustomerRepository customerRepositoryMock;
    @Mock
    private CarRepository carRepositoryMock;
    @Mock
    private LoggerService loggerServiceMock;
    @Mock
    private CarServiceImpl carServiceImplMock;
    @Mock
    private Principal principalMock;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private Car car;
    private Customer customer;




    @BeforeEach
    void setUp() {
    }

    //addOrder----------------------------------------------------------
    @Test
    void addOrder() {
    }

    //cancelOrder----------------------------------------------------------
    @Test
    void cancelOrder() {
    }


    //DeleteOneOrder----------------------------------------------------------
    @Test
    void deleteOrderThrowsIfNotExisting() {

    }




}