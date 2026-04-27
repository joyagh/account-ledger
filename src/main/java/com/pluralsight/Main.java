package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import static java.time.LocalTime.now;

public class Main {
    //scanner outside the main method so it can be used by other methods
   static Scanner scanner = new Scanner(System.in);
   static boolean isRunning = true;

    public static void main(String[] args) {
        // Scanner for input
        //test that we're able to access transactions.csv file

        displayHomeMenu();

    }


    // Home Screen Menu
    /* homeScreen -- switch in while loop?

     */
    static void displayHomeMenu(){

        //can create a while loop so that if a user makes an error the
        //menu reappears until they make a proper selection
        //or exit the app

        while(isRunning) {


            System.out.println("""
                    Welcome to your Acct Ledger! Choose from the following
                        D- Deposit
                        P- Payment
                        L- Ledger
                        X- Exit
                    
                    """);

            String selection = scanner.nextLine().trim().toUpperCase();
            switch (selection) {
                case "D":
                    Deposit();
                    break;

                case "P":
                    Payment();
                    break;

                case "L":
                    //navigate to ledger menu
                    //call the method that displays the menu or view
                    viewLedger();
                    break;

                case "X":
                    System.out.println("Thank you! Have a great day");
                    isRunning = false;

                    break;
                default:
                    System.out.println("Sorry invalid option");

            } // end of the switch statement

       } //end of the while loop

    }

    // Read transactions.csv
    static void readFromFile(){

    }

    static void  writeToFile(LocalDate date, LocalTime time, String description, String vendor, double amount){

        // try catch because something can possibly go
        // wrong when trying to access the file
        //ex - file could me misspelled, the file could not exists
        String filename = "transactions.csv";

        try(BufferedWriter bw = new BufferedWriter( new FileWriter(new File(filename),true))){
            bw.write(date + "|" + time+ "|" + description+ "|" + vendor + "|" + String.format("$%.2f", amount) + "\n");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Deposit method - Prompt for description, vendor, amount, save to csv

    static void Deposit(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String description = "Deposit";
        System.out.println("Please Enter Depositor's Name: ");
        String vendor = scanner.nextLine();
        System.out.println("Please Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        writeToFile(date, time, description, vendor,amount);
    }

    // Make Payment method - Prompt for description, vendor, amount, save to csv
    static void Payment(){
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String description = "Payment";
        System.out.println("Please Enter Payee: ");
        String vendor = scanner.nextLine();
        System.out.println("Please Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -amount;
        writeToFile(date, time, description, vendor,amount);
    }

    /* Show Ledger Method

    A - All - displayAll()
    D - Deposits - displayDeposits()
    P - Payments - displayPayments()
    R - Navigate to R
     */

    //create Ledger menu
    public static void viewLedger(){

    }

   //  R -Reports - displayReports()

    //reports m
}
