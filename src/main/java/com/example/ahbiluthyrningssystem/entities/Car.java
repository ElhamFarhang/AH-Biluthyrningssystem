package com.example.ahbiluthyrningssystem.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 15, nullable = false)
    private Double pricePerDay;

    @Column(length = 30, nullable = false)
    private String make;

    @Column(length = 30, nullable = false)
    private String model;

    @Column(length = 20, nullable = false)
    private String registrationNumber;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapKey(name = "id")
    private Map<Integer, Order> orders = new HashMap<>();

    public Car() {
    }

    public Car(Integer id, Double pricePerDay, String make, String model, String registrationNumber, Map<Integer, Order> orders) {
        this.id = id;
        this.pricePerDay = pricePerDay;
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<Integer, Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", pricePerDay=" + pricePerDay +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", orders=" + orders +
                '}';
    }
}
