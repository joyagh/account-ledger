package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Transaction {
    // Fields for Transaction
    private LocalDate date;
    private LocalTime time;
    String description;
    String vendor;
    double amount;



    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        //hint you can instruct the class to print out exactly like this
        //it will be easier for writing to the csv file (aka saving the transaction
//        date|time|description|vendor|amount
//        2023-04-15|10:13:25|ergonomic keyboard|Amazon|-89.50
//        2023-04-15|11:15:00|Invoice 1001 paid|Joe|1500.00
return  getDate() + "|" + getTime() + "|" +  getDescription() + "|"+  getVendor() + "|"+ getAmount();
    }

}
