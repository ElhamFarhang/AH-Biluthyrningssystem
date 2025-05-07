package com.example.ahbiluthyrningssystem.entities;

import jakarta.persistence.Entity;

import java.util.Map;


public class Stats {

    private Map<String, Integer> mostRentedMake;
    private Map<String, Integer> timesCarRented;
    private int mostCommonPeriodInDays;
    private double averageOrderCost;
    private Map<String, Integer> totalIncomeEveryCar;
    int totalIncomePeriod;

    public Stats() {
    }

    public int getTotalIncomePeriod() {
        return totalIncomePeriod;
    }

    public void setTotalIncomePeriod(int totalIncomePeriod) {
        this.totalIncomePeriod = totalIncomePeriod;
    }

    public Map<String, Integer> getTotalIncomeEveryCar() {
        return totalIncomeEveryCar;
    }

    public void setTotalIncomeEveryCar(Map<String, Integer> totalIncomeEveryCar) {
        this.totalIncomeEveryCar = totalIncomeEveryCar;
    }

    public double getAverageOrderCost() {
        return averageOrderCost;
    }

    public void setAverageOrderCost(double averageOrderCost) {
        this.averageOrderCost = averageOrderCost;
    }

    public int getMostCommonPeriodInDays() {
        return mostCommonPeriodInDays;
    }

    public void setMostCommonPeriodInDays(int mostCommonPeriodInDays) {
        this.mostCommonPeriodInDays = mostCommonPeriodInDays;
    }

    public Map<String, Integer> getTimesCarRented() {
        return timesCarRented;
    }

    public void setTimesCarRented(Map<String, Integer> timesCarRented) {
        this.timesCarRented = timesCarRented;
    }

    public Map<String, Integer> getMostRentedMake() {
        return mostRentedMake;
    }

    public void setMostRentedMake(Map<String, Integer> mostRentedMake) {
        this.mostRentedMake = mostRentedMake;
    }
}
