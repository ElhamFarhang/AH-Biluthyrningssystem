package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Car;
import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.entities.Order;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class OrderRepositoryTest {     //Allt Anna

    @Autowired
    private OrderRepository orderRepository;


    private Customer customer = new Customer("Ida", "Svensson", "19850101-1235", "Skåne", "Ida@mail.com");
    private Customer customer2 = new Customer("Sara", "Svensson", "19850101-9999", "Skåne", "sara@mail.com");
    private Car car = new Car(false, "reg111","9-3", "SAAB",500.0);
    private Car car2 = new Car(false, "reg222","9-3", "SAAB",500.0);
    private Order order;
    private Order order2;
    private Order order3;
    private Order order4;


    @BeforeEach
    @Transactional
    void beforeEach() {
        orderRepository.deleteAll();
        order = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customer, car);
        order2 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(5), LocalDate.now().plusDays(5),false, 55555.0,customer2, car2);
        order3 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),true, 55555.0,customer, car2);
        order4 = new Order(LocalDate.now().minusDays(20),LocalDate.now().minusDays(10), LocalDate.now().minusDays(2),true, 55555.0, customer2, car);
        orderRepository.save(order);
        orderRepository.save(order2);
        orderRepository.save(order3);
        orderRepository.save(order4);
        System.out.println(order.getDateStart());
     }


    @Test
    void findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfterShouldLeaveOneOrder() {
        List<Order> results = orderRepository.findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfter(customer.getPersonalnumber(),LocalDate.now());
        assertThat(results.size() == 1).isTrue();
        assertThat(results.get(0).isCanceled()).isFalse();
        assertThat(results.get(0).getDateEnd()).isAfter(LocalDate.now());
    }


    @Test
    void findByCustomerPersonalnumberAndCanceledTrueOrDateEndBeforeShouldLeaveOneOrder() {
        List<Order> results = orderRepository.findByCustomerPersonalnumberAndCanceledTrueOrDateEndBefore(customer.getPersonalnumber(),LocalDate.now());
        assertThat(results.size() == 1).isTrue();
        boolean canceled = results.get(0).isCanceled();
        boolean endDatePassed = LocalDate.now().isBefore(results.get(0).getDateEnd());
        assertThat(canceled||endDatePassed).isTrue();
    }


    @Test
    void findByCanceledFalseAndDateEndAfterShouldGetTwoOrders() {
        List<Order> results = orderRepository.findByCanceledFalseAndDateEndAfter(LocalDate.now());
        assertThat(results.size() == 2).isTrue();
        assertThat(results.get(0).isCanceled()).isFalse();
        assertThat(results.get(1).isCanceled()).isFalse();
        assertThat(results.get(0).getDateEnd()).isAfter(LocalDate.now());
        assertThat(results.get(1).getDateEnd()).isAfter(LocalDate.now());
    }

    @Test
    void findByCanceledTrueOrDateEndBeforeShouldGetTwoOrders() {
        List<Order> results = orderRepository.findByCanceledTrueOrDateEndBefore(LocalDate.now());
        assertThat(results.size() == 2).isTrue();
        assertThat(results.get(0).isCanceled()).isTrue();
        assertThat(results.get(1).isCanceled()).isTrue();
        assertThat(results.get(0).getDateEnd()).isBefore(LocalDate.now());
        assertThat(results.get(1).getDateEnd()).isBefore(LocalDate.now());
    }


    @Test
    void deleteByDateEndBeforeShouldLeaveTwoOrders() {
        orderRepository.deleteByDateEndBefore(LocalDate.now());
        List<Order> results = orderRepository.findAll();
        assertThat(results.size() == 2).isTrue();
        assertThat(results.get(0).getDateEnd()).isAfter(LocalDate.now());
        assertThat(results.get(1).getDateEnd()).isAfter(LocalDate.now());
    }


    @Test
    void findByCarRegistrationNumber(){
        List<Order> results = orderRepository.findByCarRegistrationNumber(order.getCar().getRegistrationNumber());
        assertThat(results.size() == 2).isTrue();
        assertThat(results.get(0).getCar().getRegistrationNumber()).isEqualTo(order.getCar().getRegistrationNumber());
        assertThat(results.get(0).getCar().getRegistrationNumber()).isEqualTo(results.get(1).getCar().getRegistrationNumber());
    }

    @Test
    void findByDateEndBetween(){
        List<Order> results = orderRepository.findByDateEndBetween(LocalDate.now(),LocalDate.now().plusDays(7));
        assertThat(results.size() == 2).isTrue();
        assertThat(results.get(0).getDateEnd()).isAfter(LocalDate.now());
        assertThat(results.get(1).getDateEnd()).isAfter(LocalDate.now());
        assertThat(results.get(0).getDateEnd()).isBefore(LocalDate.now().plusDays(7));
        assertThat(results.get(1).getDateEnd()).isBefore(LocalDate.now().plusDays(7));
    }

}