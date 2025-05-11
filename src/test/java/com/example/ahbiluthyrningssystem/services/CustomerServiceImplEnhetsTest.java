package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//--------------------- Elham - class CustomerServiceImplEnhetsTest --------------
@ExtendWith(MockitoExtension.class)
class CustomerServiceImplEnhetsTest {
    @Mock
    private CustomerRepository mockCustomerRepository;
    @InjectMocks
    private CustomerServiceImpl mockCustomerService;
    @Mock(lenient = true)
    private Principal mockPrincipal;
    private Customer testCustomer;

    @BeforeEach
    void setup() {
        testCustomer = new Customer("Sara", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        testCustomer.setId(1);
        when(mockPrincipal.getName()).thenReturn(testCustomer.getPersonalnumber());
    }

    @Test
    void getAllCustomersShouldReturnAllCustomers() {
        when(mockCustomerRepository.findAll()).thenReturn(List.of(testCustomer));
        List<Customer> customers = mockCustomerService.getAllCustomers();
        assertThat(customers.size()).isEqualTo(1);
    }

    @Test
    void getCustomerByIdShouldReturnCustomer() {
        when(mockCustomerRepository.findById(1)).thenReturn(Optional.of(testCustomer));
        Customer foundCustomer = mockCustomerService.getCustomerById(1);
        assertThat(foundCustomer).isNotNull();
        assertEquals("19850512-1230", foundCustomer.getPersonalnumber());
    }

    @Test
    void getCustomerShouldThrowResourceNotFoundExceptionWhenCustomerNotFound() {
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> mockCustomerService.deleteCustomerById(9999));
        assertThat(result.getMessage()).isEqualTo("Customer with id '9999' not found");
    }

    @Test
    void addCustomerShouldReturnCustomer() {
        when(mockCustomerRepository.save(testCustomer)).thenReturn(testCustomer);
        Customer addedCustomer = mockCustomerService.addCustomer(testCustomer);
        assertThat(addedCustomer).isNotNull();
        assertEquals("19850512-1230", addedCustomer.getPersonalnumber());
    }

    @Test
    void addCustomerShouldThrowBadRequestExceptionWhenFirstNameIsNull() {
        Customer newCustomer = new Customer("", "Åhlen", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        BadRequestException result = assertThrows(BadRequestException.class, ()-> mockCustomerService.addCustomer(newCustomer));
        assertThat(result.getMessage()).isEqualTo("FirstName and lastName required");
    }
    @Test
    void addCustomerShouldThrowBadRequestExceptionWhenLastNameIsNull() {
        Customer newCustomer = new Customer("Sara", "", "19850512-1230", "Skåne", "Sara@mail.com", "0728645678");
        BadRequestException result = assertThrows(BadRequestException.class, ()-> mockCustomerService.addCustomer(newCustomer));
        assertThat(result.getMessage()).isEqualTo("FirstName and lastName required");
    }

    @Test
    void addCustomerShouldThrowBadRequestExceptionWhenPersonalNumberIsNull() {
        Customer newCustomer = new Customer("Sara", "Åhlen", "", "Skåne", "Sara@mail.com", "0728645678");
        BadRequestException result = assertThrows(BadRequestException.class, ()-> mockCustomerService.addCustomer(newCustomer));
        assertThat(result.getMessage()).isEqualTo("Personal_number required");
    }

    @Test
    void addCustomerShouldThrowBadRequestExceptionWhenAddressIsNull() {
        Customer newCustomer = new Customer("Sara", "Åhlen", "19850512-1230", "", "Sara@mail.com", "0728645678");
        BadRequestException result = assertThrows(BadRequestException.class, ()-> mockCustomerService.addCustomer(newCustomer));
        assertThat(result.getMessage()).isEqualTo("Address required");
    }

    @Test
    void addCustomerShouldThrowBadRequestExceptionWhenEmailIsNull() {
        Customer newCustomer = new Customer("Sara", "Åhlen", "19850512-1230", "Skåne", "", "0728645678");
        BadRequestException result = assertThrows(BadRequestException.class, ()-> mockCustomerService.addCustomer(newCustomer));
        assertThat(result.getMessage()).isEqualTo("Email required");
    }

    @Test
    void updateInfoShouldReturnCustomer() {
        testCustomer.setFirst_name("Sa");
        testCustomer.setLast_name("Åh");
        testCustomer.setAddress("Stockholm");
        when(mockCustomerRepository.save(testCustomer)).thenReturn(testCustomer);
        when(mockCustomerRepository.findByPersonalnumber("19850512-1230")).thenReturn(Optional.of(testCustomer));
        Customer customerToUpdate = mockCustomerService.updateInfo(testCustomer, mockPrincipal);
        assertThat(customerToUpdate).isNotNull();
        assertThat(customerToUpdate.getPersonalnumber()).isEqualTo(testCustomer.getPersonalnumber());
        assertThat(customerToUpdate.getId()).isEqualTo(testCustomer.getId());
        assertThat(customerToUpdate.getFirst_name()).isEqualTo(testCustomer.getFirst_name());
        assertThat(customerToUpdate.getLast_name()).isEqualTo(testCustomer.getLast_name());
        assertThat(customerToUpdate.getAddress()).isEqualTo(testCustomer.getAddress());
        assertThat(customerToUpdate.getEmail()).isEqualTo(testCustomer.getEmail());
        assertThat(customerToUpdate.getPhone_number()).isEqualTo(testCustomer.getPhone_number());
    }

    @Test
    void updateInfoShouldThrowNotAcceptableExceptionWhenPersonalNumberDoesNotMatch() {
        Customer customerToUpdate = new Customer("Sara", "Åhlen", "19850512-4567", "Skåne", "Sara@mail.com", "0728645678");
        when(mockCustomerRepository.findByPersonalnumber("19850512-1230")).thenReturn(Optional.of(testCustomer));
        NotAcceptableException result = assertThrows( NotAcceptableException.class, ()-> mockCustomerService.updateInfo(customerToUpdate, mockPrincipal));
        assertThat(result.getMessage()).isEqualTo("personal_number 19850512-4567 does not match");
    }

    @Test
    void deleteCustomerByIdShouldRemoveCustomer() {
        when(mockCustomerRepository.existsById(testCustomer.getId())).thenReturn(true);
        mockCustomerService.deleteCustomerById(testCustomer.getId());
        assertFalse(mockCustomerRepository.findById(testCustomer.getId()).isPresent());
    }

    @Test
    void deleteCustomerByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        ResourceNotFoundException result = assertThrows(ResourceNotFoundException.class, () -> mockCustomerService.deleteCustomerById(9999));
        assertThat(result.getMessage()).isEqualTo("Customer with id '9999' not found");
    }
}
//--------------------- Elham - class CustomerServiceImplEnhetsTest --------------