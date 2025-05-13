package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
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
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@Transactional
@Rollback
class CustomerControllerOrderServiceOrderRepositoryIntegrationTest {

    private final CustomerController customerController;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final Customer customer = new Customer("Ida", "Svensson", "19850101-1235", "Skåne", "Ida@mail.com",null);
    private final Customer customer2 = new Customer("Sara", "Svensson", "19850101-9999", "Skåne", "sara@mail.com",null);
    //private final Car car = new Car(false, "reg111","9-3", "SAAB",500.0);
    //private final Car car2 = new Car(false, "reg222","9-3", "SAAB",500.0);
    private Order order;
    private Order order2;
    private Order order3;
    private Order order4;


    @Mock
    private CarService carServiceMock;

    @InjectMocks
    private OrderService orderService;



    @Autowired
    public CustomerControllerOrderServiceOrderRepositoryIntegrationTest(OrderRepository orderRepository, OrderService orderService, CustomerRepository customerRepository, CustomerService customerServiceMock) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.customerRepository = customerRepository;
        this.customerController = new CustomerController(customerServiceMock, carServiceMock,orderService);
    }


    @BeforeEach
    void setUp() {
        //Given
        Authentication authentication = new UsernamePasswordAuthenticationToken("19850101-1235", null, List.of());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        orderRepository.deleteAll();
        customerRepository.deleteAll();
        //order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customer, car);
        //order2 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customer2, car2);
        //order3 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),true, 55555.0,customer, car2);
        //order4 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(10), LocalDate.now().plusDays(2),false, 55555.0, customer, car);
        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        customerRepository.save(customer);
        customerRepository.save(customer2);
    }

    @Test
    void ShouldOnlyReturnActiveOrdersForLoggedInCustomer() {
        // When
        ResponseEntity<List<Order>> response = customerController.getActiveOrders();
        List<Order> orderList = response.getBody();
        // Then
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.OK));
        assertThat(orderList).isNotNull();
        assertThat(orderList.size()).isEqualTo(2);
        assertThat(orderList.get(0)).isEqualTo(order);
        assertThat(orderList.get(1)).isEqualTo(order4);
        assertThat(orderList.get(0).isCanceled()).isFalse();
        assertThat(orderList.get(0).getDateEnd()).isAfter(LocalDate.now());
        assertThat(orderList.get(1).isCanceled()).isFalse();
        assertThat(orderList.get(1).getDateEnd()).isAfter(LocalDate.now());
    }

}
