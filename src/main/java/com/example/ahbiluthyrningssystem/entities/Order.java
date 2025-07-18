package com.example.ahbiluthyrningssystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ORDERS")
public class Order {                //Anna
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @Column(length = 12, nullable = false)
    private LocalDate dateCreated;
    @Column(length = 12, nullable = false)
    private LocalDate dateStart;
    @Column(length = 12, nullable = false)
    private LocalDate dateEnd;
    @Column(length = 10, nullable = false)
    private boolean canceled;
    @Column(length = 10, nullable = false)
    private Double totalCost = 0.0;
    @JsonIgnoreProperties("orders")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Customer customer;
    @JsonIgnoreProperties("orders")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private Car car;


    public Order() {
    }

    public Order(LocalDate dateStart, LocalDate dateEnd, boolean canceled, Customer customer) {
        this.dateCreated = LocalDate.now();
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.canceled = canceled;
        this.customer = customer;
    }

    public Order(LocalDate dateCreated, LocalDate dateStart, LocalDate dateEnd, boolean canceled, Double totalCost, Customer customer, Car car) {
        this.dateCreated = dateCreated;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.canceled = canceled;
        this.totalCost = totalCost;
        this.customer = customer;
        this.car = car;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", canceled=" + canceled +
                ", totalCost=" + totalCost +
                ", customer=" + customer +
                ", car=" + car +
                '}';
    }
}