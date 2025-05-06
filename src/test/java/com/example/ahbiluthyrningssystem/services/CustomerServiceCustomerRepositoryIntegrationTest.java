package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//--------------------- Elham - class CustomerServiceImplIntegrationTest --------------
@SpringBootTest
class CustomerServiceCustomerRepositoryIntegrationTest {

    private CustomerRepository customerRepository;
    private CustomerServiceImpl customerService;
    private static Customer customer;
    private static Customer savedCustomer;
    private Principal mockPrincipal;

    @Autowired
    public CustomerServiceCustomerRepositoryIntegrationTest(CustomerRepository customerRepository, CustomerServiceImpl customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @BeforeEach
    void beforeEach() {
        customerRepository.deleteAll();
        customer = new Customer("Sara", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        savedCustomer = customerRepository.save(customer);
        mockPrincipal = ()->customer.getPersonalnumber();
    }

    @Test
    void getAllCustomersShouldReturnAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(customers.size(), 1);
    }

    @Test
    void getCustomerByIdShouldReturnCustomer() {
        Customer foundCustomer = customerService.getCustomerById(savedCustomer.getId());
        assertThat(foundCustomer.getPersonalnumber()).isEqualTo(customer.getPersonalnumber());
    }
    @Test
    void getCustomerByIdShouldThrowException() {
        Integer id = 111;
        assertThrows(ResourceNotFoundException.class, ()-> customerService.getCustomerById(id));
    }

    @Test
    void addCustomerShouldReturnCustomer() {
        Customer foundCustomer = customerService.addCustomer(savedCustomer);
        assertThat(foundCustomer.getPersonalnumber()).isEqualTo(customer.getPersonalnumber());
    }

    @Test
    void addCustomerShouldThrowException() {
        customer = new Customer("", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        assertThrows(BadRequestException.class, ()-> customerService.addCustomer(customer));
    }

    @Test
    void updateInfoShouldReturnCustomer() {
        Customer foundCustomer = customerService.updateInfo(savedCustomer, mockPrincipal);
        assertThat(foundCustomer.getPersonalnumber()).isEqualTo(customer.getPersonalnumber());
    }

    @Test
    void updateInfoShouldThrowException() {
        savedCustomer = new Customer("Sara", "Åhlen", "19850512-4567", "Skåne", "Sara@mail.com", "0728645678");
        assertThrows( NotAcceptableException.class, ()-> customerService.updateInfo(savedCustomer, mockPrincipal));
    }

    @Test
    void deleteCustomerByIdShouldRemoveCustomer() {
        customerService.deleteCustomerById(savedCustomer.getId());
        assertFalse(customerRepository.findById(savedCustomer.getId()).isPresent());
    }

    @Test
    void deleteCustomerByIdShouldThrowException() {
        Integer id = 1112;
        assertThrows( ResourceNotFoundException.class, ()-> customerService.deleteCustomerById(id));
    }
}
//--------------------- Elham - class CustomerServiceImplIntegrationTest --------------