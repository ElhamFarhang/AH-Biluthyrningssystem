package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotAvailable;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {         //Allt Anna

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
    private Car carMock;
    @Mock
    private Customer customerMock;


    @InjectMocks
    private OrderServiceImpl orderService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void addOrderShouldSaveAndReturnOrderWhenValid() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),true, 55555.0,customerMock, carMock);
//        when(orderRepositoryMock.save(order)).thenReturn(order);
        when(carMock.getId()).thenReturn(1);
        when(carMock.getPricePerDay()).thenReturn(1.0);
        when(loggerServiceMock.getLoggedInUser()).thenReturn("1");
        when(carRepositoryMock.findById(1)).thenReturn(Optional.of(carMock));
        when(customerRepositoryMock.findByPersonalnumber("1")).thenReturn(Optional.of(customerMock));
        when(carServiceImplMock.isCarBooked(carMock,order.getDateStart(),order.getDateEnd()).booleanValue()).thenReturn(false);
        // When
        Order savedOrder = orderService.addOrder(order);
        // Then
        assertNotNull(savedOrder);
        assertThat(order.isCanceled()).isFalse();
        assertEquals(order.getTotalCost(), 10.0);
        assertEquals(order.getDateCreated(), LocalDate.now());
        assertEquals(order, savedOrder);
    }

    @Test
    void addOrderShouldThrowExceptionWhenDateStartIsNull() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customerMock, carMock);
        order.setDateStart(null);
        // When & Then
        BadRequestException exception = assertThrows(BadRequestException.class, () ->
                orderService.addOrder(order));
        assertEquals("Start date are required", exception.getMessage());
    }

    @Test
    void addOrderShouldThrowExceptionWhenDateEndIsNull() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customerMock, carMock);
        order.setDateEnd(null);
        // When & Then
        BadRequestException exception = assertThrows(BadRequestException.class, () ->
                orderService.addOrder(order));
        assertEquals("End date are required", exception.getMessage());
    }


    @Test
    void addOrderShouldThrowExceptionWhenGetCarIsNull() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customerMock, carMock);
        order.setCar(null);
        // When & Then
        BadRequestException exception = assertThrows(BadRequestException.class, () ->
                orderService.addOrder(order));
        assertEquals("Car are required", exception.getMessage());
    }


    @Test
    void addOrderShouldThrowExceptionWhenOptionalCarIsEmpty() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customerMock, carMock);
        when(carMock.getId()).thenReturn(1);
        when(carRepositoryMock.findById(1)).thenReturn(Optional.empty());
        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                orderService.addOrder(order));
        assertEquals("Car with id '1' not found", exception.getMessage());
    }

    @Test
    void addOrderShouldThrowExceptionWhenOptionalCustomerIsEmpty() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customerMock, carMock);
        when(carMock.getId()).thenReturn(1);
        when(loggerServiceMock.getLoggedInUser()).thenReturn("1");
        when(carRepositoryMock.findById(1)).thenReturn(Optional.of(carMock));
        when(customerRepositoryMock.findByPersonalnumber("1")).thenReturn(Optional.empty());
        // When & Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                orderService.addOrder(order));
        assertEquals("Customer with Personalnumber '1' not found", exception.getMessage());
    }

    @Test
    void addOrderShouldThrowExceptionWhenCarIsBooked() {
        //Given
        Order order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customerMock, carMock);
        when(carMock.getId()).thenReturn(1);
        when(loggerServiceMock.getLoggedInUser()).thenReturn("1");
        when(carRepositoryMock.findById(1)).thenReturn(Optional.of(carMock));
        when(carServiceImplMock.isCarBooked(carMock,order.getDateStart(),order.getDateEnd()).booleanValue()).thenReturn(true);
        // When & Then
        ResourceNotAvailable exception = assertThrows(ResourceNotAvailable.class, () ->
                orderService.addOrder(order));
        assertEquals("Car not available for this period", exception.getMessage());
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