package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {        //Anna

    private Principal principal;
    private final OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private static final Logger FUNCTIONALITY_LOGGER = LogManager.getLogger("functionality");
    private String userName;
    private Car car = new Car();            //TODO ta bort rad 30 + 36 + 78

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        car.setPricePerDay(500);            //TODO ta bort
    }

    @Override
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }


    //  Wille & Anna
    @Override
    public Order addOrder(Order order) {
        Order newOrder = order;
        userName = principal.getName();
        newOrderCheckAndSetDetails(newOrder);
        orderRepository.save(newOrder);
        FUNCTIONALITY_LOGGER.info("Order nr {} added by {}", newOrder.getId(), userName);             //TODO Lägga in admin /username
        return newOrder;
    }

    private void newOrderCheckAndSetDetails(Order newOrder){        //Anna
        if (newOrder.getDateStart()==null){
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
        newOrder.setCanceled(false);
        newOrder.setDateCreated(LocalDate.now());
        Optional<Customer> thisCustomer = customerRepository.findByPersonalnumber(userName);
        if (thisCustomer.isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("A not logged in user tried to add a order");
            throw new ResourceNotFoundException("Customer", "Personal_number", userName);
       }
        newOrder.setCustomer(thisCustomer.get());
        int days = (int) ChronoUnit.DAYS.between(newOrder.getDateStart(), newOrder.getDateEnd());
        newOrder.setTotalCost(days*car.getPricePerDay());       //TODO fixa car till car.getPrice
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
    public List<Order> getActiveOrdersCustomer() {        //Anna
        LocalDate today = LocalDate.now();
        userName = principal.getName();
        FUNCTIONALITY_LOGGER.info("Active orders retrieved by {}", userName);
        return orderRepository.findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfter(userName, today);
    }

    @Override
    public List<Order> getOldOrdersCustomer() {        //Anna
        LocalDate today = LocalDate.now();
        userName = principal.getName();
        FUNCTIONALITY_LOGGER.info("Old orders retrieved by {}", userName);
        return orderRepository.findByCustomerPersonalnumberAndCanceledTrueOrDateEndBefore(userName, today);
    }

    @Override
    public List<Order> getActiveOrdersAdmin() {        //Anna
        LocalDate today = LocalDate.now();
        FUNCTIONALITY_LOGGER.info("All active orders retrieved by admin");
        return orderRepository.findByCanceledFalseAndDateEndAfter(today);
    }

    @Override
    public List<Order> getOldOrdersAdmin() {        //Anna
        LocalDate today = LocalDate.now();
        FUNCTIONALITY_LOGGER.info("All old orders retrieved by admin");
        return orderRepository.findByCanceledTrueOrDateEndBefore(today);
    }

    @Override
    public void deleteOrder(Integer id) {        //Anna
        Optional<Order> orderToDelete = orderRepository.findById(id);
        if (!orderToDelete.isPresent())
            throw new ResourceNotFoundException("Order", "id", id);
        orderRepository.deleteById(id);
        FUNCTIONALITY_LOGGER.info("Order nr {} deleted by admin", id );

    }

    @Override
    public void deleteAllOrdersBeforeDate(LocalDate date) {        //Anna
        orderRepository.deleteByDateEndBefore(date);
        FUNCTIONALITY_LOGGER.info("Orders ended before: {} deleted by admin", date);
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
