package com.example.ahbiluthyrningssystem.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.ahbiluthyrningssystem.entities.Customer;
import com.example.ahbiluthyrningssystem.repositories.CustomerRepository;

//  Wille
@Service
public class LoggerServiceImpl implements LoggerService {

    private CustomerRepository customerRepo;
    private final Logger FUNC_LOGGER = LogManager.getLogger("functionality");
    private final Logger ERR_LOGGER = LogManager.getLogger("errors");

    @Autowired
    public LoggerServiceImpl(CustomerRepository repo){
        this.customerRepo = repo;
    }

    @Override
    public void logInfo(String msg){
        FUNC_LOGGER.info("{} {}", getUserFullName(), msg);
    }
	
    @Override
	public void logWarn(String msg) {
        FUNC_LOGGER.warn("{} {}", getUserFullName(), msg);
	}

	@Override
	public void logErr(String msg) {
        ERR_LOGGER.error("{} {}", getUserFullName(), msg);
	}

    @Override
    public String getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    private String getUserFullName(){
        String user = getLoggedInUser();

        if(user.equals("admin"))
            return user;

        Optional<Customer> customer = customerRepo.findByPersonalnumber(user);
        if(customer.isPresent())
            user = String.format("Customer %s %s", customer.get().getFirst_name(), customer.get().getLast_name());
        else  {  
            //  If a customer doesn't exist with that ssn, the entire ssn won't be leaked in the logs. GDPR respected.
            String error = String.format(
                "Failed to get customer from principal %s, does the customer exist?", 
                user.contains("-") ? user.substring(0,user.indexOf('-')) : user
            );
            ERR_LOGGER.error(error);
            System.out.println("\u001B[31m"+error+"\u001B[0m");
            user = "Unknown user";
        }
        return user;
    }

    
}
