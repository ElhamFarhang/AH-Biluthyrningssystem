package com.example.ahbiluthyrningssystem.repositories;

import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {   //Anna

    List<Order> findByCustomerPersonalnumberAndCanceledFalseAndDateEndAfter(String customer, LocalDate date);

    @Query("SELECT o FROM Order o WHERE o.customer.personalnumber = :customer AND (o.canceled = true OR o.dateEnd < :date)")
    List<Order> findByCustomerPersonalnumberAndCanceledTrueOrDateEndBefore(@Param("customer") String customer, @Param("date") LocalDate date);

    List<Order> findByCanceledFalseAndDateEndAfter(LocalDate dateEndAfter);
    List<Order> findByCanceledTrueOrDateEndBefore(LocalDate dateEndBefore);


    List<Order> findByDateEndBefore(LocalDate cutOffDate);
    void deleteByDateEndBefore(LocalDate cutOffDate);

    List<Order> findByCarRegistrationNumber(String carRegistrationNumber);
    List<Order> findByDateEndBetween(LocalDate dateEndAfter, LocalDate dateEndBefore);




}
