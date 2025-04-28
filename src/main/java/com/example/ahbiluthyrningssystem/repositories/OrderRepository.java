package com.example.ahbiluthyrningssystem.repositories;


import com.example.ahbiluthyrningssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {   //Anna

    List<Order> findByCustomerIdAndActiveTrue(Integer customerId);
    List<Order> findByCustomerIdAndActiveFalse(Integer customerId);
    List<Order> findByCustomerIdAndActiveFalse(Integer customerId);
    List<Order> findByActiveTrue();
    List<Order> findByActiveFalse();



/*     /api/v1/admin/activeorders- Lista alla aktiva ordrar
 • GET /api/v1/admin/orders - Lista historiska ordrar
 • DELETE /api/v1/admin/removeorder- Ta bort bokning från systemet
 • DELETE /api/v1/admin/removeorders-beforedate/{date*/


}
