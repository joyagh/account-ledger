package com.pluralsight;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean isRunning = true;

    public static void main(String[] args) {
        displayHomeMenu();
    }

    static void displayHomeMenu() {

        while (isRunning) {


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
                    viewLedger();
                    break;

                case "X":
                    System.out.println("Thank you! Have a great day");
                    isRunning = false;

                    break;
                default:
                    System.out.println("Sorry invalid option");

            }

        }
    }

    static ArrayList<Transaction> readFromFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String fileName = "transactions.csv";


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse((parts[0]));
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date,time,description,vendor,amount));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;

    }

    static void writeToFile(LocalDate date, LocalTime time, String description, String vendor, double amount) {

        String filename = "transactions.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true))) {
            bw.write(date + "|" + time + "|" + description + "|" + vendor + "|" +  String.format("%.2f", amount) + "\n");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void Deposit() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String description = "Deposit";
        System.out.println("Please Enter Depositor's Name: ");
        String vendor = scanner.nextLine();
        System.out.println("Please Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        writeToFile(date, time, description, vendor, amount);
    }

    static void Payment() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String description = "Payment";
        System.out.println("Please Enter Payee: ");
        String vendor = scanner.nextLine();
        System.out.println("Please Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -amount;
        writeToFile(date, time, description, vendor, amount);
    }


    public static void viewLedger() {
        ArrayList<Transaction> transactions = readFromFile();

            for (Transaction item : transactions) {
                System.out.println(item.getDate() + "|" + item.getTime() + "|" + item.getDescription() + "|" + item.getVendor() + "|" + item.getAmount());

            }

        }
    }




