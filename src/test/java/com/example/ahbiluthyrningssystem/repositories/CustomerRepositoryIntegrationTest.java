package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//--------------------- Elham - class CustomerRepositoryIntegrationTest --------------
@DataJpaTest
class CustomerRepositoryIntegrationTest {

    @Autowired
    private CustomerRepository customerRepository;

    private static Customer customer;

    @BeforeEach
    void beforeEach() {
        customer = new Customer("Anna", "Svensson", "19850101-1234", "Skåne", "Anna@mail.com", "0728686636");
    }

    @Test
    void findByPersonalnumberShouldReturnCustomer() {
        Optional<Customer> customerFound = customerRepository.findByPersonalnumber(customer.getPersonalnumber());
        assertThat(customerFound.isPresent()).isTrue();
    }
}
//--------------------- Elham - class CustomerRepositoryIntegrationTest --------------