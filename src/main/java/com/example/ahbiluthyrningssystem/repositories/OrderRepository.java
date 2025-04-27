package com.example.ahbiluthyrningssystem.repositories;


import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {   //Anna



}
