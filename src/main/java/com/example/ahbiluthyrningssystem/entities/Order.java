package com.example.ahbiluthyrningssystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "`order`")
public class Order {                //Anna
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 12, nullable = false)
    private Date dateCreated;
    @Column(length = 12, nullable = false)
    private Date dateStart;
    @Column(length = 12, nullable = false)
    private Date dateEnd;
    @Column(length = 12, nullable = false)
    private int daysTotal;                      //behövs?
    @Column(length = 10, nullable = false)
    private boolean active;
    @Column(length = 10, nullable = false)
    private boolean canceled=false;
    @Column(length = 10, nullable = false)
    private int totalCost;
    //@JsonIgnoreProperties("orders")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Car car;


    public Order() {
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getDaysTotal() {
        return daysTotal;
    }

    public void setDaysTotal(int daysTotal) {
        this.daysTotal = daysTotal;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
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
}
