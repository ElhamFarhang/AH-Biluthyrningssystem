package com.example.ahbiluthyrningssystem.repositories;


import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {   //Anna

    List<Order> findByCustomerIdAndActiveTrue(Integer customerId);
    List<Order> findByCustomerIdAndActiveFalse(Integer customerId);
    List<Order> findByActiveTrue();
    List<Order> findByActiveFalse();
    void deleteByDateEndBefore(Date cutOffDate);

}
