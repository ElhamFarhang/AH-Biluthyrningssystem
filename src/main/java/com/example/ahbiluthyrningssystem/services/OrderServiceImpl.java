package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotAvailableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {     // Det mesta Anna
    private final CarServiceImpl carServiceImpl;
    private final OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private CarRepository carRepository;
    private final LoggerService LOG;
    private String userName;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, CarRepository carRepository, LoggerService LOG, CarServiceImpl carServiceImpl) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.LOG = LOG;
        this.carServiceImpl = carServiceImpl;
    }


    //  Wille & Anna
    @Override
    public Order addOrder(Order order) {
        Order newOrder = order;
        userName = LOG.getLoggedInUser();
        newOrderCheckAndSetDetails(newOrder);
        orderRepository.save(newOrder);
        LOG.logInfo("added new order with id " + order.getId());
        return newOrder;
    }

    private void newOrderCheckAndSetDetails(Order newOrder){        //Anna
        if (newOrder.getDateStart()==null){
            LOG.logWarn("tried to add an order without a start date");
            throw new BadRequestException("Start date");
        }
        if (newOrder.getDateEnd()==null){
            LOG.logWarn("tried to add an order without an end date");
            throw new BadRequestException("End date");
        }
        if (newOrder.getCar()==null){
            LOG.logWarn("tried to add a new order without a car");
            throw new BadRequestException("Car");
        }
        Optional<Car> optionalCar = carRepository.findById(newOrder.getCar().getId());
        if (optionalCar.isEmpty()) {
            LOG.logWarn(String.format("tried to add a non-existing car with id %s to the order.", newOrder.getCar().getId()));
            throw new ResourceNotFoundException("Car", "id", newOrder.getCar().getId());
        }
        if (carServiceImpl.isCarBooked(optionalCar.get(), newOrder.getDateStart(), newOrder.getDateEnd())){
            LOG.logWarn(String.format("tried to add a car during dates it's already booked."));
            throw new ResourceNotAvailableException("Car", "period");
        }
        newOrder.setCar(optionalCar.get());
        newOrder.setCanceled(false);
        newOrder.setDateCreated(LocalDate.now());
        Optional<Customer> thisCustomer = customerRepository.findByPersonalnumber(userName);
        if (thisCustomer.isEmpty()) {
            throw new ResourceNotFoundException("Customer", "Personalnumber", userName);
       }
        newOrder.setCustomer(thisCustomer.get());
        int days = (int) ChronoUnit.DAYS.between(newOrder.getDateStart(), newOrder.getDateEnd());
        newOrder.setTotalCost(days*newOrder.getCar().getPricePerDay());
    }


    @Override
    public List<Order> getActiveOrdersCustomer() {        //Anna
        LocalDate today = LocalDate.now();
        userName = LOG.getLoggedInUser();
        LOG.logInfo("retrieved active orders");
        return orderRepository.findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfter(userName, today);
    }

    @Override
    public List<Order> getOldOrdersCustomer() {        //Anna
        LocalDate today = LocalDate.now();
        userName = LOG.getLoggedInUser();
        LOG.logInfo("retrieved old orders");
        return orderRepository.findByCustomerPersonalnumberAndCanceledTrueOrDateEndBefore(userName, today);
    }

    @Override
    public List<Order> getActiveOrdersAdmin() {        //Anna
        LocalDate today = LocalDate.now();
        LOG.logInfo("retrieved all active orders");
        return orderRepository.findByCanceledFalseAndDateEndAfter(today);
    }

    @Override
    public List<Order> getOldOrdersAdmin() {        //Anna
        LocalDate today = LocalDate.now();
        LOG.logInfo("retrieved old orders");
        return orderRepository.findByCanceledTrueOrDateEndBefore(today);
    }

    @Override
    public void deleteOrder(Integer id) {        //Anna
        Optional<Order> orderToDelete = orderRepository.findById(id);
        if (!orderToDelete.isPresent())
            throw new ResourceNotFoundException("Order", "id", id);
        orderRepository.deleteById(id);
        LOG.logInfo("deleted order with id " + id);
    }

    @Override
    public void deleteAllOrdersBeforeDate(LocalDate date) {        //Anna
        orderRepository.deleteByDateEndBefore(date);
        LOG.logInfo("deleted orders before " + date);
    }

    // Elham - cancelOrder
    @Override
    public void cancelOrder(Integer id) {
        Optional<Order> orderToCancel = orderRepository.findById(id);
        if (!orderToCancel.isPresent())
            throw new ResourceNotFoundException("Order", "id", id);
        else {
            Order order = orderToCancel.get();
            updateCanceledOrder(order);
            orderRepository.save(order);
        }
    }

    private void updateCanceledOrder(Order order) {
        if (!order.getCustomer().getPersonalnumber().equals(LOG.getLoggedInUser())) {
            throw new ResourceNotAvailableException("Order", "user to cancel");
        }
        if (order.isCanceled()){
            throw new ResourceNotAvailableException("Cancellation", "already canceled order");
        }
        if (order.getDateStart().isBefore(LocalDate.now().plusDays((1)))) {
            throw new ResourceNotAvailableException("Cancellation", "order as it's to old");
        }
        if (order.isCanceled()){
            throw new ResourceNotAvailableException("Cancellation", "already canceled order");
        }
        if (order.getDateStart().isBefore(LocalDate.now().plusDays((1)))) {
            throw new ResourceNotAvailableException("Cancellation", "order as it's to old");
        }
        order.setCanceled(true);
//      order.getCar(). //TODO ta bort datum från bilens isBooked. Väntar på Theo.
        order.setCar(null);
        int daysBeforeStart = (int) ChronoUnit.DAYS.between(LocalDate.now(), order.getDateStart());
        Double newCost;
        System.out.println(daysBeforeStart);
        if (daysBeforeStart <= 7)
            newCost = order.getTotalCost()*0.5;
        else
            newCost = 0.0;
        order.setTotalCost(newCost);
    }

}
