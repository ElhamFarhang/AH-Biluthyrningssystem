package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import java.security.Principal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


//--------------------- Elham - class CustomerControllerCustomerServiceCustomerRepositoryIntegrationTest --------------

@SpringBootTest
@Transactional
@Rollback
class CustomerControllerCustomerServiceCustomerRepositoryIntegrationTest {

    private CustomerController customerController;
    private AdminController adminController;
    Customer testCustomer, invalidCustomer;
    private Principal mockPrincipal;

    // Elham
    @Autowired
    public CustomerControllerCustomerServiceCustomerRepositoryIntegrationTest(AdminController adminController, CustomerController customerController) {
        this.adminController = adminController;
        this.customerController = customerController;
    }

    // Elham
    @BeforeEach
    void beforeEach() {
        testCustomer = new Customer("Sara", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        adminController.addCustomer(testCustomer);
        mockPrincipal = ()->testCustomer.getPersonalnumber();
    }

    // Elham
    @Test
    void updateInfoShouldReturnCustomer() {
        ResponseEntity<Customer> response = customerController.updateInfo(testCustomer, mockPrincipal);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    // Elham
    @Test
    void updateInfoShouldThrowException() {
        invalidCustomer = new Customer("Sara", "Åhlen", "19850512-4567", "Skåne", "Sara@mail.com", "0728645678");
        assertThrows( NotAcceptableException.class, ()-> customerController.updateInfo(invalidCustomer, mockPrincipal));

        NotAcceptableException result = assertThrows(NotAcceptableException.class, () -> customerController.updateInfo(invalidCustomer, mockPrincipal));
        assertThat(result.getMessage()).isEqualTo("personal_number 19850512-4567 does not match");

    }
}