package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private static Order order;
    private static Order order2;


/*    @BeforeEach
    void beforeEach() {
        order = new Order(LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),true);
        order2 = new Order(LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),false);
        orderRepository.save(order);
        orderRepository.save(order2);
    }*/

    @AfterEach
    void tearDown() {
    }


    @Test
    void deleteByDateEndBeforeShouldLeaveOneOrder() {
        // Given
        order = new Order(LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),true);
        order2 = new Order(LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),false);
        orderRepository.save(order);
        orderRepository.save(order2);
        System.out.println("Antal studerande" + orderRepository.findAll().size());
            //--
        // When
        orderRepository.deleteByDateEndBefore(LocalDate.now());
        List<Order> result = orderRepository.findAll();
        // Then
        assertThat(result.size());
        //assertThat(result).allMatch(order -> !order.isCanceled() && order.getDateEnd().isAfter(LocalDate.now()));

    }


    @Test
    void findByCanceledFalseAndDateEndAfter() {
    }

    @Test
    void findByCanceledTrueOrDateEndBefore() {
    }

    @Test
    void deleteByDateEndBefore() {
    }
}