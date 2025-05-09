package com.example.ahbiluthyrningssystem.entities;

import jakarta.persistence.*;

import java.util.Date;

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

    @Column(length = 10, nullable = false)
    private boolean isBooked;

    public Car() {
    }

    public Car(String make, String model, int pricePerDay, String registrationNumber) {
        this.make = make;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.registrationNumber = registrationNumber;
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

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", pricePerDay=" + pricePerDay +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", isBooked=" + isBooked +
                '}';
    }
}
