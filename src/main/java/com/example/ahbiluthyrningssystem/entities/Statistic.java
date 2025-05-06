package com.example.ahbiluthyrningssystem.entities;

public class Statistic {

    private record rentedCarBrand(String make, int rentedTimes){
        public String getMake(){
            return make;
        }
        public int getRentedTimes(){
            return rentedTimes;
        }
    }



}
