package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import com.example.ahbiluthyrningssystem.repositories.OrderRepository;
import com.example.ahbiluthyrningssystem.services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class AdminControllerOrderServiceOrderRepositoryIntegrationTest {   //Anna

    private final AdminController adminController;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private Map<LocalDate, Order> carOrders = new HashMap<>();
    private Map<LocalDate, Order> carOrders2 = new HashMap<>();
    private final Customer customer = new Customer("Ida", "Svensson", "19850101-1235", "Skåne", "Ida@mail.com",null);
    private final Customer customer2 = new Customer("Sara", "Svensson", "19850101-9999", "Skåne", "sara@mail.com",null);
    private final Car car = new Car(500.0,"SAAB", "9-3", "reg111",carOrders);
    private final Car car2 = new Car(500.0, "SAAB","9-3","reg222", carOrders2);
    private Order order;
    private Order order2;
    private Order order3;
    private Order order4;

    @Mock
    private CarService carServiceMock;

    @Mock
    private CustomerService customerServiceMock;
    @Mock
    private StatisticsServiceImpl statisticsServiceMock;


    @InjectMocks
    private OrderService orderService;



    @Autowired
    public AdminControllerOrderServiceOrderRepositoryIntegrationTest(OrderRepository orderRepository, OrderService orderService, CustomerRepository customerRepository, CustomerService customerServiceMock) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.customerRepository = customerRepository;
        this.adminController = new AdminController(customerServiceMock, carServiceMock, orderService,statisticsServiceMock);
    }

    @BeforeEach
    void setUp() {
        //Given
        Authentication authentication = new UsernamePasswordAuthenticationToken("19850101-1235", null, List.of());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customer, car);
        order2 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customer2, car2);
        order3 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),true, 55555.0,customer, car2);
        order4 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(10), LocalDate.now().plusDays(2),false, 55555.0, customer, car);
        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        customerRepository.save(customer);
        customerRepository.save(customer2);
    }


    @Test
    void deleteOrderShouldReturnStatusCode200AndMessage() {
        //Given
        List<Order> orderListBefore = orderRepository.findAll();
        int toDelete = orderListBefore.get(2).getId();
        // When
        ResponseEntity<String> response = adminController.deleteOrder(toDelete);
        List<Order> orderListAfter = orderRepository.findAll();
        // Then
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));
        assertThat(orderListBefore.size()).isEqualTo(orderListAfter.size()+1);
        assertThat(response.getBody()).isEqualTo("Order with Id: " + toDelete + " has been successfully deleted.");
        assertThat(orderListAfter.contains(orderListBefore.get(2))).isFalse();
    }


    @Test
    void deleteNonExistingOrderShouldThrowStatusCode404ResourceNotFoundException() {
        List<Order> orderListBefore = orderRepository.findAll();
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () ->
                adminController.deleteOrder(-1));
        List<Order> orderListAfter = orderRepository.findAll();
        assertThat(orderListBefore.size()).isEqualTo(orderListAfter.size());
        assertThat(exception.getMessage()).isEqualTo("Order with id '-1' not found");
    }
}
