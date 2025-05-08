package com.example.ahbiluthyrningssystem.repositories;


import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class OrderRepositoryTest {             //Anna

    @Autowired
    private OrderRepository orderRepository;


    private Customer customer = new Customer("Ida", "Svensson", "19850101-1235", "Skåne", "Ida@mail.com");
    private Order order;
    private Order order2;


    @BeforeEach
    void beforeEach() {
        orderRepository.deleteAll();
        order = new Order(LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, customer);
        order2 = new Order(LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),true, customer);
        orderRepository.save(order);
        orderRepository.save(order2);
    }


    @Test
    void deleteByDateEndBeforeShouldLeaveOneOrder() {
        orderRepository.deleteByDateEndBefore(LocalDate.now());
        List<Order> results = orderRepository.findAll();
        assertThat(results.size()==1);
        assertThat(results.get(0).getDateEnd()).isAfter(LocalDate.now());
    }


    @Test
    void findByCanceledFalseAndDateEndAfterShouldGetOneOrder() {
        List<Order> results = orderRepository.findByCanceledFalseAndDateEndAfter(LocalDate.now());
        assertThat(results.size()==1);
        assertThat(results.get(0).isCanceled()).isFalse();
        assertThat(results.get(0).getDateEnd()).isAfter(LocalDate.now());
    }

    @Test
    void findByCanceledTrueOrDateEndBeforeShouldGetOneOrder() {
        List<Order> results = orderRepository.findByCanceledTrueOrDateEndBefore(LocalDate.now());
        assertThat(results.size()==1);
        assertThat(results.get(0).isCanceled()).isTrue();
        assertThat(results.get(0).getDateEnd()).isBefore(LocalDate.now());
    }

}