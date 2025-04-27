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


//--------------------- Elham - CustomerServiceImp --------------
@Service
public class CustomerServiceImp implements CustomerServiceInterface {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateInfo(Integer id, Customer customer) {
        Optional<Customer> customerToUpdate = customerRepository.findById(id);
        if (!customerToUpdate.isPresent())
            throw new ResourceNotFoundException("Customer", "id", id);
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty())
            throw new BadRequestException("FirstName and lastName");
        if (customer.getEmail().isEmpty())
            throw new BadRequestException("Email");
        if (customer.getAddress().isEmpty())
            throw new BadRequestException("Address");
        if (!(customerToUpdate.get().getPersonal_number().equals(customer.getPersonal_number()) ))
            throw new NotAcceptableException(customer.getPersonal_number());
        customer.setCustomer_id(id);
        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Integer id) {
        return null;
    }

}
