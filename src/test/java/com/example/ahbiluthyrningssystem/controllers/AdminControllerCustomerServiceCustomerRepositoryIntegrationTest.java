package com.example.ahbiluthyrningssystem.controllers;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
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
import static org.junit.jupiter.api.Assertions.*;


//--------------------- Elham - class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest --------------
@SpringBootTest
@Transactional
@Rollback
class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest {

    private AdminController adminController;
    private CustomerRepository customerRepository;
    Customer testCustomer, invalidCustomer;

    @Autowired
    public AdminControllerCustomerServiceCustomerRepositoryIntegrationTest(AdminController adminController,CustomerRepository customerRepository) {
        this.adminController = adminController;
        this.customerRepository = customerRepository;
    }

    @BeforeEach
    void beforeEach() {
        testCustomer = new Customer("Sara", "Åhlen", "19850912-1678", "Skåne", "Sara@mail.com", "0728645678");
        invalidCustomer = new Customer("", "", "19850512-1984", "Skåne", "Alice@mail.com", "0728645678");
    }

    @Test
    void getAllCustomersShouldReturnStatusCode200AndAllCustomers() {
        ResponseEntity<List<Customer>> response = adminController.getAllCustomers();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(customerRepository.findAll().size());
    }

    @Test
    void getCustomerByIdShouldReturnStatusCode200AndCustomerBody() {
        adminController.addCustomer(testCustomer);
        ResponseEntity<Customer> response = adminController.getCustomerById(testCustomer.getId());
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
    }

    @Test
    void getCustomerShouldThrowStatusCode404NotFoundException() {
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> adminController.getCustomerById(99999));
        assertThat(result.getMessage()).isEqualTo("Customer with id '99999' not found");
    }

    @Test
    void addCustomerShouldReturnStatusCode200AndCustomerBody() {
        ResponseEntity<Customer> response =adminController.addCustomer(testCustomer);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
        assertThat(response.getBody()).isEqualTo(testCustomer);
    }

    @Test
    void addCustomerShouldReturnStatusCode400BadRequestException() {
        BadRequestException result = assertThrows(BadRequestException.class, () -> adminController.addCustomer(invalidCustomer));
        assertThat(result.getMessage()).isEqualTo("FirstName and lastName required");
    }

    @Test
    void deleteCustomerByIdShouldStatusCode200AndRemoveCustomer() {
        adminController.addCustomer(testCustomer);
        Integer id = testCustomer.getId();
        ResponseEntity<String> response = adminController.deleteCustomerById(id);
        assertThat(response.getStatusCode().isSameCodeAs(HttpStatus.OK)).isTrue();
        assertThat(response.getBody()).isEqualTo("Customer with Id: " + id + " has been successfully deleted.");
    }
    
    @Test
    void deleteCustomerByIdShouldThrowStatusCode404NotFoundException() {
        Integer id = 1112;
        assertThrows( ResourceNotFoundException.class, ()-> adminController.deleteCustomerById(id));
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> adminController.deleteCustomerById(id));
        assertThat(result.getMessage()).isEqualTo("Customer with id '1112' not found");
    }
}
//--------------------- Elham - class AdminControllerCustomerServiceCustomerRepositoryIntegrationTest --------------
