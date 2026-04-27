package com.pluralsight;

import java.time.LocalDateTime;

public class Transaction {
    // Fields for Transaction
    private LocalDateTime date;
    private LocalDateTime time;
    String description;
    String vendor;
    double amount;

    // Transaction Constructor
    public Transaction(){

    }

    public Transaction(LocalDateTime date, LocalDateTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
return  super.toString();
    }

}
