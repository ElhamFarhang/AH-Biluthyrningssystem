package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {   //Anna

    List<Order> findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfter(String customer, LocalDate date);
    List<Order> findByCustomerPersonalnumberAndCanceledTrueOrDateEndBefore(String customer, LocalDate date);

    List<Order> findByCanceledFalseAndDateEndAfter(LocalDate dateEndAfter);
    List<Order> findByCanceledTrueOrDateEndBefore(LocalDate dateEndBefore);

    void deleteByDateEndBefore(LocalDate cutOffDate);

    List<Order> findByCarRegistrationNumber(String carRegistrationNumber);
    List<Order> findByDateEndBetween(LocalDate dateEndAfter, LocalDate dateEndBefore);
}
