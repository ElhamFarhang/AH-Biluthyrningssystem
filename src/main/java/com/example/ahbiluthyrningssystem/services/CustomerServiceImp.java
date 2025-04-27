package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CarRepository;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//--------------------- Elham - CustomerServiceImp --------------
@Service
public class CustomerServiceImp implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository, CarRepository carRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    //??
    @Override
    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Integer id) {
        Optional<Order> orderToCancel = orderRepository.findById(id);
        if (!orderToCancel.isPresent())
            throw new ResourceNotFoundException("Order", "id", id);
        else {
            Order order = orderToCancel.get();
            order.setCanceled(true);
            orderRepository.save(order);
        }
    }

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

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Customer updateInfo(Integer id, Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);
        if (!customerToUpdate.isPresent())
            throw new ResourceNotFoundException("Customer", "id", id);
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty())
            throw new BadRequestException("FirstName and lastName");
        if (customer.getEmail().isEmpty())
            throw new BadRequestException("Email");
        if (customer.getAddress() == null)
            throw new BadRequestException("Address");
        if (!(customerToUpdate.get().getPersonal_number().equals(customer.getPersonal_number()) ))
            throw new NotAcceptableException(customer.getPersonal_number());
        customer.setCustomer_id(id);
        return customerRepository.save(customer);
    }
}
