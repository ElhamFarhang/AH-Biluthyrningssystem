package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//--------------------- Elham - class CustomerServiceImp --------------
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final LoggerService LOG;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, LoggerService LOG) {
        this.customerRepository = customerRepository;
        this.LOG = LOG;
    }

    @Override
    public List<Customer> getAllCustomers() {
        LOG.logInfo("retrieved all customers from the database.");
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            LOG.logWarn("tried to retrieve a non-existent customer");
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        LOG.logInfo("retrieved a customer with id:" + id);
        return customer.get();
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty()) {
            LOG.logWarn(("tried to add an invalid customer"));
            throw new BadRequestException("FirstName and lastName");
        }
        if (customer.getPersonalnumber().isEmpty()) {
            LOG.logWarn(("tried to add an invalid customer"));
            throw new BadRequestException("Personal_number");
        }
        if (customer.getAddress().isEmpty()) {
            LOG.logWarn(("tried to add an invalid customer"));
            throw new BadRequestException("Address_Id");
        }
        if (customer.getEmail().isEmpty()) {
            LOG.logWarn(("tried to add an invalid customer"));
            throw new BadRequestException("Email");
        }
        LOG.logInfo("added a new customer");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateInfo(Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findByPersonalnumber(LOG.getLoggedInUser());
        if (optionalCustomer.isEmpty()) {
            LOG.logWarn("tried to update a non-existent customer");
            throw new ResourceNotFoundException("Customer", "Personal_number", LOG.getLoggedInUser());
        }
        Customer customerToUpdate = optionalCustomer.get();
        customer.setId(customerToUpdate.getId());
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty()) {
            LOG.logWarn(("tried to update an invalid customer"));
            throw new BadRequestException("FirstName and lastName");
        }
        if (customer.getEmail().isEmpty()) {
            LOG.logWarn(("tried to update an invalid customer"));
            throw new BadRequestException("Email");
        }
        if (customer.getAddress().isEmpty()) {
            LOG.logWarn(("tried to update an invalid customer"));
            throw new BadRequestException("Address");
        }
        if (!(customer.getPersonalnumber().equals(LOG.getLoggedInUser()))) {
            LOG.logWarn("tried to update a customer with a mismatched personal number");
            throw new NotAcceptableException(customer.getPersonalnumber());
        }
        customerToUpdate.setPersonalnumber(LOG.getLoggedInUser());
        LOG.logInfo(String.format("with id %s updated her/his information.", customer.getId()));
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            LOG.logWarn("tried to delete an invalid customer");
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        LOG.logInfo("deleted customer with id:" + id);
        customerRepository.deleteById(id);
    }
}
//--------------------- Elham - class CustomerServiceImp --------------
