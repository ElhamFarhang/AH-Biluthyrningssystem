package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.services.CustomerService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//--------------------- Elham - class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest --------------
@SpringBootTest
@Transactional
class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest {

    private AdminController adminController;
    private CustomerService customerService;
    Customer testCustomer, invalidCustomer;

    // Elham
    @Autowired
    public AdminControllerCustomerServiceCustomerRepositoryIntegrationTest(AdminController adminController,CustomerService customerService) {
        this.adminController = adminController;
        this.customerService = customerService;
    }

    // Elham
    @BeforeEach
    void beforeEach() {
        testCustomer = new Customer("Sara", "Åhlen", "19850912-1678", "Skåne", "Sara@mail.com", "0728645678");
        invalidCustomer = new Customer("", "", "19850512-1984", "Skåne", "Alice@mail.com", "0728645678");
    }

    // Elham
    @Test
    void getAllCustomersShouldReturnAllCustomers() {
        ResponseEntity<List<Customer>> response = adminController.getAllCustomers();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(customerService.getAllCustomers().size());
    }

    // Elham
    @Test
    void getCustomerByIdShouldReturnCustomer() {
        adminController.addCustomer(testCustomer);
        ResponseEntity<Customer> response = adminController.getCustomerById(testCustomer.getId());
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
    }

    // Elham
    @Test
    void getCustomerShouldReturnException() {
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> adminController.getCustomerById(99999));
        assertThat(result.getMessage()).isEqualTo("Customer with id '99999' not found");
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
    void addCustomerShouldReturnException() {
        BadRequestException result = assertThrows(BadRequestException.class, () -> adminController.addCustomer(invalidCustomer));
        assertThat(result.getMessage()).isEqualTo("FirstName and lastName required");
    }

    // Elham
    @Test
    void deleteCustomerByIdShouldRemoveCustomer() {
        adminController.addCustomer(testCustomer);
        Integer id = testCustomer.getId();
        adminController.deleteCustomerById(id);
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> adminController.getCustomerById(id));
        assertThat(result.getMessage()).isEqualTo("Customer with id '"+id+"' not found");
    }
    // Elham
    @Test
    void deleteCustomerByIdShouldThrowException() {
        Integer id = 1112;
        assertThrows( ResourceNotFoundException.class, ()-> adminController.deleteCustomerById(id));
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> adminController.deleteCustomerById(id));
        assertThat(result.getMessage()).isEqualTo("Customer with id '1112' not found");
    }
}

