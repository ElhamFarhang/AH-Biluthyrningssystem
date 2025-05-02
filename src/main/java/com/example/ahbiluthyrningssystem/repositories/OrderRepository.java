package com.example.ahbiluthyrningssystem.repositories;


import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {   //Anna

    List<Order> findByCustomerIdAndCanceledFalseAndDateEndAfter(Integer customerId, Date date);
    List<Order> findByCustomerIdAndCanceledTrueOrDateEndBefore(Integer customerId, Date date);

    List<Order> findByCanceledFalseAndDateEndAfter(Date dateEndAfter);
    List<Order> findByCanceledTrueOrDateEndBefore(Date dateEndBefore);
    void deleteByDateEndBefore(Date cutOffDate);

}
