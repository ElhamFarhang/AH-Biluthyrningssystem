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
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


//--------------------- Elham - class CustomerServiceCustomerRepositoryIntegrationTest --------------
@SpringBootTest
@Transactional
@Rollback
class CustomerServiceCustomerRepositoryIntegrationTest {

    private CustomerRepository customerRepository;
    private CustomerServiceImpl customerService;
    private static Customer savedCustomer;
    private Principal mockPrincipal = mock(Principal.class);

    @Autowired
    public CustomerServiceCustomerRepositoryIntegrationTest(CustomerRepository customerRepository, CustomerServiceImpl customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @BeforeEach
    void beforeEach() {
        customerRepository.deleteAll();
        savedCustomer = customerRepository.save( new Customer("Sara", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678"));
        mockPrincipal = ()->savedCustomer.getPersonalnumber();
    }

    @Test
    void getAllCustomersShouldReturnAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        assertEquals(customers.size(), 1);
    }

    @Test
    void getCustomerByIdShouldReturnCustomer() {
        Customer foundCustomer = customerService.getCustomerById(savedCustomer.getId());
        assertThat(foundCustomer.getPersonalnumber()).isEqualTo(savedCustomer.getPersonalnumber());
    }
    @Test
    void getCustomerByIdShouldThrowException() {
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomerById(9999));
        assertThat(result.getMessage()).isEqualTo("Customer with id '9999' not found");
    }

    @Test
    void addCustomerShouldReturnCustomer() {
        Customer newCustomer = customerService.addCustomer(new Customer("Sten", "Åhlen", "19850512-1879", "Skåne", "Sten@mail.com", "0728645678"));
        assertThat(newCustomer.getPersonalnumber()).isEqualTo("19850512-1879");
    }

    @Test
    void addCustomerShouldThrowException() {
        Customer newCustomer = new Customer("", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        BadRequestException result = assertThrows(BadRequestException.class, ()-> customerService.addCustomer(newCustomer));
        assertThat(result.getMessage()).isEqualTo("FirstName and lastName required");
    }

    @Test
    void updateInfoShouldReturnCustomer() {
        Customer customer= savedCustomer;
        customer.setFirst_name("Sa");
        customer.setLast_name("Åh");
        customer.setAddress("Stockholm");
        Customer customerToUpdate = customerService.updateInfo(customer,mockPrincipal);
        assertThat(customerToUpdate.getPersonalnumber()).isEqualTo(savedCustomer.getPersonalnumber());
        assertThat(customerToUpdate.getFirst_name()).isEqualTo(savedCustomer.getFirst_name());
        assertThat(customerToUpdate.getLast_name()).isEqualTo(savedCustomer.getLast_name());
        assertThat(customerToUpdate.getAddress()).isEqualTo(savedCustomer.getAddress());
    }

    @Test
    void updateInfoShouldThrowException() {
        Customer customerToUpdate = new Customer("Sara", "Åhlen", "19850512-4567", "Skåne", "Sara@mail.com", "0728645678");
        NotAcceptableException result = assertThrows( NotAcceptableException.class, ()-> customerService.updateInfo(customerToUpdate,mockPrincipal));
        assertThat(result.getMessage()).isEqualTo("personal_number 19850512-4567 does not match");
    }

    @Test
    void deleteCustomerByIdShouldRemoveCustomer() {
        customerService.deleteCustomerById(savedCustomer.getId());
        assertFalse(customerRepository.findById(savedCustomer.getId()).isPresent());
    }

    @Test
    void deleteCustomerByIdShouldThrowException() {
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomerById(9999));
        assertThat(result.getMessage()).isEqualTo("Customer with id '9999' not found");
    }
}
//--------------------- Elham - class CustomerServiceCustomerRepositoryIntegrationTest --------------
