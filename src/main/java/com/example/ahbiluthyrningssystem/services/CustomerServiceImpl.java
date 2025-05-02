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
    private Principal principal;
    private static final Logger FUNCTIONALITY_LOGGER = LogManager.getLogger("functionality");

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Integer id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent())
            throw new ResourceNotFoundException("Customer", "id", id);
        return customer.get();
    }

    //  Wille
    @Override
    public Customer getCustomerBySSN(String ssn) {
        List<Customer> customers = customerRepository.findAll();
        Customer loggedInUser = customers.stream().filter(c -> c.getPersonal_number().equals(principal.getName())).findFirst().orElse(null);
        if(loggedInUser == null)
            throw new ResourceNotFoundException("Customer", "ssn", ssn);
        return loggedInUser;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add an invalid customer");
            throw new BadRequestException("FirstName and lastName");
        }
        if (customer.getPersonal_number().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add an invalid customer");
            throw new BadRequestException("Personal_number");
        }
        if (customer.getAddress().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add an invalid customer");
            throw new BadRequestException("Address_Id");
        }
        if (customer.getEmail().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to add an invalid customer");
            throw new BadRequestException("Email");
        }
        FUNCTIONALITY_LOGGER.info("A new customer by the name {} has been saved", customer.getFirst_name());
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateInfo(Integer id, Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);
        if (!customerToUpdate.isPresent()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update an invalid customer");
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update an invalid customer");
            throw new BadRequestException("FirstName and lastName");
        }
        if (customer.getEmail().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update an invalid customer");
            throw new BadRequestException("Email");
        }
        if (customer.getAddress().isEmpty()) {
            FUNCTIONALITY_LOGGER.warn("User tried to update an invalid customer");
            throw new BadRequestException("Address");
        }
        if (!(customerToUpdate.get().getPersonal_number().equals(customer.getPersonal_number()))) {
            FUNCTIONALITY_LOGGER.warn("User tried to update an invalid customer");
            throw new NotAcceptableException(customer.getPersonal_number());
        }
        customer.setCustomer_id(id);
        FUNCTIONALITY_LOGGER.info("Customer by the id:{} updated", customer.getCustomer_id());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            FUNCTIONALITY_LOGGER.warn("User tried to delete an invalid customer");
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        FUNCTIONALITY_LOGGER.info("Customer by the id:{} deleted",id);
        customerRepository.deleteById(id);
    }
}
//--------------------- Elham - class CustomerServiceImp --------------
