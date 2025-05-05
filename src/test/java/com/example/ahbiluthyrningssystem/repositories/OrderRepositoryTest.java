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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    private static Order order;


    @BeforeEach
    void beforeEach() {
        order = new Order(LocalDate.of(2025,05,01), LocalDate.of(2025,05,05));
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void existsStudentByEmailShouldReturnTrueTest() {
        // Given

        // When

        // Then
    }

    @Test
    void findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfter() {
    }

    @Test
    void findByCustomerPersonalnumberAndCanceledTrueOrDateEndBefore() {
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