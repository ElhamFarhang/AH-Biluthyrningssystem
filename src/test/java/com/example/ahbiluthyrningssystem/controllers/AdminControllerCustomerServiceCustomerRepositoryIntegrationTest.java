package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.services.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

//--------------------- Elham - class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest --------------
@SpringBootTest
@Transactional
@Rollback
class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest {

    private AdminController adminController;
    private CustomerService customerService;
    Customer testCustomer;

    // Elham
    @Autowired
    public AdminControllerCustomerServiceCustomerRepositoryIntegrationTest(AdminController adminController, CustomerService customerService) {
        this.adminController = adminController;
        this.customerService = customerService;
    }

    // Elham
    @BeforeEach
    void beforeEach() {
        testCustomer = new Customer("Sara", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
    }

    // Elham
    @Test
    void getAllCustomers() {

    }

    // Elham
    @Test
    void getCustomerById() {
        ResponseEntity<Customer> response =adminController.getCustomerById(testCustomer.getId());
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
//        assertThat(response.()).isEqualTo(testCustomer.getId());

//        Customer foundCustomer = customerService.getCustomerById(savedCustomer.getId());
//        assertThat(foundCustomer.getPersonalnumber()).isEqualTo(customer.getPersonalnumber());
    }

    // Elham
    @Test
    void addCustomerShouldReturnStatusCode200() {
        ResponseEntity<Customer> response =adminController.addCustomer(testCustomer);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
        assertThat(response.getBody()).isEqualTo(testCustomer);
    }

    // Elham
    @Test
    void deleteCustomerById() {
    }
}