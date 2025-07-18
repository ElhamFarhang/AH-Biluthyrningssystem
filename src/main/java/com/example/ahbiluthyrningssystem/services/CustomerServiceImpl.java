package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


//--------------------- Elham - class CustomerServiceImp --------------
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private static final Logger FUNCTIONALITY_LOGGER = LogManager.getLogger("functionality");

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        FUNCTIONALITY_LOGGER.info("All customers are being retrieved from the database.");
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            FUNCTIONALITY_LOGGER.warn("User tried to access a non-existent customer");
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        FUNCTIONALITY_LOGGER.info("Retrieving customer with ID: {}", id);
        return customer.get();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add a customer with missing or invalid fields");
            throw new BadRequestException("FirstName and lastName");
        }
        if (customer.getPersonalnumber().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add a customer with missing or invalid fields");
            throw new BadRequestException("Personal_number");
        }
        if (customer.getAddress().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add a customer with missing or invalid fields");
            throw new BadRequestException("Address");
        }
        if (customer.getEmail().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add a customer with missing or invalid fields");
            throw new BadRequestException("Email");
        }
        FUNCTIONALITY_LOGGER.info("New customer '{}' has been successfully saved", customer.getFirst_name());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateInfo(Customer customer, Principal principal) {
        Optional<Customer> optionalCustomer = customerRepository.findByPersonalnumber(principal.getName());
        if (optionalCustomer.isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User attempted to update a non-existent customer with personal number: {}", principal.getName());
            throw new ResourceNotFoundException("Customer", "Personal_number", principal.getName());
        }
        Customer customerToUpdate = optionalCustomer.get();
        customer.setId(customerToUpdate.getId());
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update a customer with invalid data");
            throw new BadRequestException("FirstName and lastName");
        }
        if (customer.getEmail().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update a customer with invalid data");
            throw new BadRequestException("Email");
        }
        if (customer.getAddress().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update a customer with invalid data");
            throw new BadRequestException("Address");
        }
        if (!(customer.getPersonalnumber().equals(principal.getName()))) {
            FUNCTIONALITY_LOGGER.warn("User tried to update a customer with a mismatched personal number.");
            throw new NotAcceptableException(customer.getPersonalnumber());
        }
        customerToUpdate.setPersonalnumber(principal.getName());
        FUNCTIONALITY_LOGGER.info("Customer with ID:{} has been updated.", customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            FUNCTIONALITY_LOGGER.warn("User tried to delete a customer that does not exist");
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        FUNCTIONALITY_LOGGER.info("Customer by the id:{} deleted",id);
        customerRepository.deleteById(id);
    }
}
//--------------------- Elham - class CustomerServiceImp --------------