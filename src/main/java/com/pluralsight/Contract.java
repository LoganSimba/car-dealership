package com.pluralsight;

import java.math.BigDecimal;

public abstract class Contract {

    private String date;
    private String name;
    private String email;
    private Vehicle vehicle;

    //constructor for contract class,
    public Contract(String date, String name, String email, Vehicle vehicle) {
        this.date = date;
        this.name = name;
        this.email = email;
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    //method for total price that is begDecimal, since vehicle price is bigDecimal
    public abstract BigDecimal getTotalPrice();

    //method for monthlypayment that is begDecimal, since vehicle price is bigDecimal
    public abstract BigDecimal getMonthlyPayment();
}