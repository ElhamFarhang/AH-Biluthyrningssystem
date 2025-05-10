package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
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

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

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
    /*        Order newOrder = order;
        userName = principal.getName();
        newOrderCheckAndSetDetails(newOrder);
        orderRepository.save(newOrder);
        FUNCTIONALITY_LOGGER.info("Order nr {} added by {}", newOrder.getId(), userName);
        return newOrder;*/

    /*    if (newOrder.getDateStart()==null){
            FUNCTIONALITY_LOGGER.warn("{} tried to add an order with out a start date", userName);
            throw new BadRequestException("Start date");
        }
        if (newOrder.getDateEnd()==null){
            FUNCTIONALITY_LOGGER.warn("{} tried to add an order with out an end date", userName);
            throw new BadRequestException("End date");
        }
        if (newOrder.getCar()==null){
            FUNCTIONALITY_LOGGER.warn("{} tried to add an order with out a car", userName);
            throw new BadRequestException("Car");
        }
        Optional<Car> optionalCar = carRepository.findById(newOrder.getCar().getId());
        if (optionalCar.isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("{} tried to add a non-existing car with id: {} to the order.", principal.getName(), newOrder.getCar().getId());
            throw new ResourceNotFoundException("Car", "id", newOrder.getCar().getId());
        }
        if (carServiceImpl.isCarBooked(optionalCar.get(), newOrder.getDateStart(), newOrder.getDateEnd())){
            FUNCTIONALITY_LOGGER.warn("{} tried to add a car during dates it's already booked.", principal.getName());
            //TODO skriv nytt exception
        }
        newOrder.setCar(optionalCar.get());
        newOrder.setCanceled(false);
        newOrder.setDateCreated(LocalDate.now());
    Optional<Customer> thisCustomer = customerRepository.findByPersonalnumber(userName);
        if (thisCustomer.isEmpty()) {
        FUNCTIONALITY_LOGGER.warn("A not logged in user tried to add a order");
        throw new ResourceNotFoundException("Customer", "Personal_number", userName);
    }
        newOrder.setCustomer(thisCustomer.get());
    int days = (int) ChronoUnit.DAYS.between(newOrder.getDateStart(), newOrder.getDateEnd());
        newOrder.setTotalCost(days*newOrder.getCar().getPricePerDay()); */
    @Test
    void addOrderShouldSaveAndReturnOrderWhenValid() {


    }


    @Test
    void addOrderShouldThrowExceptionWhenCustomerIsNull() {
    }

    @Test
    void addOrderShouldThrowExceptionWhenDateStartIsNull() {
    }

    @Test
    void addOrderShouldThrowExceptionWhenDateEndIsNull() {
    }

    @Test
    void addOrderShouldThrowExceptionWhenGetCarIsNull() {
    }


    @Test
    void addOrderShouldThrowExceptionWhenOptionalCarIsEmpty() {
    }


    @Test
    void addOrderShouldThrowExceptionWhenOptionalCustomerIsEmpty() {
    }


    @Test
    void addOrderShouldThrowExceptionWhenCarIsBooked() {
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