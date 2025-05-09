package com.example.ahbiluthyrningssystem.services;

import com.example.ahbiluthyrningssystem.entities.Stats;

import java.time.LocalDate;
import java.util.Map;

public interface StatisticsService {

  Stats getStats(LocalDate start, LocalDate End);
  Map<String, Integer> mostRentedMake(LocalDate start, LocalDate End);
  Map<String, Integer> timesCarRented();
  int mostCommonRentalPeriodInDays();
  double calculateAverageOrderCost();
  Map<String, Double> totalIncomeEveryCar();
  Double totalIncomePeriod(LocalDate start, LocalDate End);

  }
