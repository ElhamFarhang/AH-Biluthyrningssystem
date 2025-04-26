package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//--------------------- Elham - CustomerRepository --------------
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
