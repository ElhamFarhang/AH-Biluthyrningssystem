package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {        //Anna

    private Principal principal;
    private final OrderRepository orderRepository;
    private static final Logger FUNCTIONALITY_LOGGER = LogManager.getLogger("functionality");

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }


/*    @Override
    public List<Order> getAllOrders() {
        if(orderRepository.findAll().isEmpty()) {
            FUNCTIONALITY_LOGGER.info("@{}: There are no orders in the system",principal.getName()); //TODO testa admin /username
            throw new RuntimeException();                                       //TODO skapa bättre exception
        }
        FUNCTIONALITY_LOGGER.info("{} retrieved all orders",principal.getName());                   //TODO testa admin /username
        return orderRepository.findAll();
    }*/

/*    @Override
    public Order getOrderById(Integer id) {
        orderRepository.findById(id).orElseThrow(()-> {
            FUNCTIONALITY_LOGGER.info("Order nr {} requested by: {} does not exist", id, principal.getName());  //TODO testa admin /username
            return new ResourceNotFoundException("Order", "id", id);
        });
        FUNCTIONALITY_LOGGER.info("Order nr {} retrieved by: {}", id,principal.getName());               //TODO testa admin /username
        return orderRepository.findById(id).get();
    }*/

/*    @Override
    public Order updateOrder(Integer id, Order order) {
        orderRepository.findById(id).orElseThrow(()-> {
            FUNCTIONALITY_LOGGER.info("Order nr {} requested by: {} for updating does not exist", id,principal.getName());  //TODO Lägga in admin /username
            return new ResourceNotFoundException("Order", "id", id);
        });
        //Kod för att kontrollera uppdateringen                                     //TODO kontrollera uppdatering
        //Kod för att uppdatera priset
        FUNCTIONALITY_LOGGER.info("Order nr {} updated by: {}", id, principal.getName());               //TODO Lägga in admin /username
        return orderRepository.save(order);
    }*/




    //  Wille & Anna
    @Override
    public Order addOrder(Order order) {
        Order newOrder = orderRepository.save(order);
        FUNCTIONALITY_LOGGER.info("Order nr {} added by {}", newOrder.getId(), principal.getName());             //TODO Lägga in admin /username
        //Kod för att kontrollera nya ordern                                     //TODO kontrollera nya ordern
        //Kod för att uppdatera priset
        return newOrder;

    }

    @Override
    public void deleteOrder(Integer id) {
        orderRepository.findById(id).orElseThrow();  //TODO skapa exception
        FUNCTIONALITY_LOGGER.info("Order nr {} deleted by ---", id);             //TODO Lägga in admin /username
        orderRepository.deleteById(id);

    }

    @Override
    public void deleteAllOrdersBeforeDate(Date date) {
        orderRepository.deleteByDateEndBefore(date);
    }



    // Elham - cancelOrder
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
    public List<Order> getActiveOrdersCustomer() {
        Date today = new Date();
        //kod för att hitta användaren
        return orderRepository.findByCustomerIdAndCanceledFalseAndDateEndAfter(1, today);
    }

    @Override
    public List<Order> getOldOrdersCustomer() {
        Date today = new Date();
        //kod för att hitta användaren
        return orderRepository.findByCustomerIdAndCanceledTrueOrDateEndBefore(1, today);
    }

/*    // Elham & Wille
    @Override
    public List<Order> getAllOrders() {
        List<Customer> customers = customerRepository.findAll();
        Customer customer = customers.stream().filter(c -> c.getFirst_name().equals(principal.getName())).findFirst().get();
        FUNCTIONALITY_LOGGER.info("Customer {} checked orders", customer.getFirst_name());
        if(customer != null)
            return customer.getOrders();
        return null;
    }*/


    @Override
    public List<Order> getActiveOrdersAdmin() {
        Date today = new Date();
        return orderRepository.findByCanceledFalseAndDateEndAfter(today);
    }

/*
    //  Elham & Wille
    @Override
    public List<Order> getActiveOrdersAdmin() {
        LocalDate today = LocalDate.now();
        return orderRepository.findByActiveTrue();
        List<Order> orders = new ArrayList<>();
        for(Customer customer : customerRepository.findAll()) {
            orders.addAll(customer.getOrders().stream().filter(Order::isActive).toList());
        }
//        LocalDate today = LocalDate.now();
        return orders;
    }*/



    @Override
    public List<Order> getOldOrdersAdmin() {
        Date today = new Date();
        return orderRepository.findByCanceledTrueOrDateEndBefore(today);
    }

}
