package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.exceptions.BadRequestException;
import com.example.ahbiluthyrningssystem.exceptions.NotAcceptableException;
import com.example.ahbiluthyrningssystem.exceptions.ResourceNotFoundException;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


//--------------------- Elham - class CustomerServiceImp --------------
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
 //   private Principal principal;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
 //       principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 //       Integer id = getCustomerByFirstName(principal.getName());
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

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer.getFirst_name().isEmpty() || customer.getLast_name().isEmpty())
            throw new BadRequestException("FirstName and lastName");
        if (customer.getPersonal_number() == null)
            throw new BadRequestException("Personal_number");
        if (customer.getAddress() == null)
            throw new BadRequestException("Address_Id");
        if (customer.getEmail().isEmpty())
            throw new BadRequestException("Email");
        return customerRepository.save(customer);
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
        if (!(customerToUpdate.get().getPersonal_number().equals(customer.getPersonal_number())))
            throw new NotAcceptableException(customer.getPersonal_number());
        customer.setCustomer_id(id);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer", "id", id);
        }
        customerRepository.deleteById(id);
    }
}
//--------------------- Elham - class CustomerServiceImp --------------