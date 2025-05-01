package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {        //Anna

    private Principal principal;

Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    private final OrderRepository orderRepository;
    private static final Logger FUNCTIONALITY_LOGGER = LogManager.getLogger("functionality");

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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


    @Override
    public Order addOrder(Order order) {
        FUNCTIONALITY_LOGGER.info("Order nr {} added by {}", order.getId(),authentication.getName());             //TODO Lägga in admin /username
        //Kod för att kontrollera nya ordern                                     //TODO kontrollera nya ordern
        //Kod för att uppdatera priset
        return orderRepository.save(order);

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
            order.setActive(false);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> getActiveOrdersCustomer(Integer customerId) {
        return orderRepository.findByCustomerIdAndActiveTrue(customerId);
    }

    @Override
    public List<Order> getOldOrdersCustomer(Integer customerId) {
        return orderRepository.findByCustomerIdAndActiveFalse(customerId);
    }

    @Override
    public List<Order> getActiveOrdersAdmin() {
        LocalDate today = LocalDate.now();
        return orderRepository.findByActiveTrue();
    }

    @Override
    public List<Order> getOldOrdersAdmin() {
        return orderRepository.findByActiveFalse();
    }

}
