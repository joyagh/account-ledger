package com.pluralsight;
import javax.xml.crypto.dsig.TransformService;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean isRunning = true;

    public static void main(String[] args) {
        splashScreen();
        displayHomeMenu();
    }

    static void splashScreen() {
        System.out.println(Colors.GREEN + """
                
                ╔═══════════════════════════════════════════════╗
                ║   💰  CASH MONEY ACCOUNT SERVICES  💰         ║
                ║         Your money, organized.                ║
                ╚═══════════════════════════════════════════════╝
                """ + Colors.RESET);
        System.out.println(Colors.YELLOW + "        💰  Your money, organized. Welcome to CMAS.  💰" + Colors.RESET);
        System.out.println(Colors.CYAN + "        ════════════════════════════════════════════" + Colors.RESET);
        System.out.println();
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
                    deposit();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case "P":
                    payment();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case "L":
                    viewLedger();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
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
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }

        } catch (IOException e) {
            System.out.println("Error loading file. Please try again.");;
        }
        return transactions;

    }

    static void writeToFile(LocalDate date, LocalTime time, String description, String vendor, double amount) {

        String filename = "transactions.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(filename), true))) {
            bw.write(date + "|" + time + "|" + description + "|" + vendor + "|" + String.format("%.2f", amount) + "\n");


        } catch (IOException e) {
            System.out.println("Error saving transaction. Please try again. ");
            e.printStackTrace();
        }
    }

    static void deposit() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withSecond(0).withNano(0);
        String description = "Deposit";
        System.out.println("Please Enter Depositor's Name: ");
        String vendor = scanner.nextLine();
        System.out.println("Please Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        writeToFile(date, time, description, vendor, amount);
        System.out.println("\uD83D\uDCB0 CHA-CHING! \uD83D\uDCB0");
    }

    static void payment() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now().withSecond(0).withNano(0);
        String description = "Payment";
        System.out.println("Please Enter Payee: ");
        String vendor = scanner.nextLine();
        System.out.println("Please Enter Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        amount = -amount;
        writeToFile(date, time, description, vendor, amount);
    }

    public static void viewLedger() {

        while (isRunning) {
            System.out.println(Colors.YELLOW + "💰 Cash Money Account Services — Ledger" + Colors.RESET);
            System.out.println(Colors.CYAN + "════════════════════════════════════════════" + Colors.RESET);

            System.out.println("""
                    Welcome to your Acct Ledger! Choose from the following
                        A - All
                        D- Deposits
                        P - Payments
                        R- Navigate to Reports
                        H- Home
                    
                    """);

            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "A":
                    ArrayList<Transaction> transactions = readFromFile();
                    displayAll(transactions);
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case "D":
                    displayDeposits();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case "P":
                    displayPayments();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case "R":
                    reports();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case "H":
                    displayHomeMenu();
                    break;
                default:
                    System.out.println(Colors.RED + "Sorry, invalid option." + Colors.RESET);

            }

        }
    }

    //display all method
    public static void displayAll(ArrayList<Transaction> transactions) {
        System.out.println(Colors.CYAN +
                String.format("%-12s %-10s %-20s %-15s %10s",
                        "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT") + Colors.RESET);
        System.out.println(Colors.CYAN + "═".repeat(72) + Colors.RESET);

//        for (Transaction item : transactions) {
//            System.out.println(item.getDate() + "|" + item.getTime() + "|" + item.getDescription() + "|" + item.getVendor() + "|" + item.getAmount());
//        }
//        System.out.println("Displaying the reversal in displaying transactions");
//        System.out.println();
        //reverse the way transactions are displayed in the console (to the user)
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction item = transactions.get(i);
            String color = item.getAmount() >= 0 ? Colors.GREEN : Colors.RED;
//            System.out.println(item.getDate() + "|" + item.getTime() + "|" + item.getDescription() + "|" + item.getVendor() + "|" + item.getAmount());
            System.out.println(color + String.format("%-12s %-10s %-20s %-15s %10s",
                    item.getDate(),
                    item.getTime(),
                    item.getDescription(),
                    item.getVendor(),
                    String.format("$%.2f", item.getAmount())) + Colors.RESET);
        }
        System.out.println();
    }

    public static void displayDeposits() {
        ArrayList<Transaction> transactions = readFromFile();
        ArrayList<Transaction> depTemp = new ArrayList<>();

        for (Transaction item : transactions) {
            if (item.getAmount() > 0) {
                depTemp.add(item);
            }
        }

        displayAll(depTemp);
    }

    public static void displayPayments() {
        ArrayList<Transaction> transactions = readFromFile();
        ArrayList<Transaction> payTemp = new ArrayList<>();

        for (Transaction item : transactions) {
            if (item.getAmount() < 0) {
                payTemp.add(item);
            }
        }
        displayAll(payTemp);
    }

    public static void reports() {

        while (isRunning) {
            System.out.println(Colors.YELLOW + "💰 Cash Money Account Services — Reports" + Colors.RESET);
            System.out.println(Colors.CYAN + "════════════════════════════════════════════" + Colors.RESET);

            System.out.println("""
                    Welcome to your Acct Ledger! Choose from the following
                        1 - Month to Date
                        2 - Previous Month
                        3 - Year to Date
                        4 - Previous Year
                        5 - Search by Vendor
                        6 - Custom Search
                        0 - Back
                    
                    """);


            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchMonthToDate();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case 2:
                    searchPreviousMonth();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case 3:
                    searchYearToDate();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case 4:
                    searchPreviousYear();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;

                case 5:
                    searchVendor();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;
                case 6:
                    customSearch();
                    System.out.println("\nPress enter to continue..");
                    scanner.nextLine();
                    break;
                case 0:
                    viewLedger();
                    break;
                default:
                    System.out.println(Colors.RED + "Sorry, invalid option." + Colors.RESET);

            }

        }
    }

    //search month to date
    public static void searchMonthToDate() {
        LocalDate today = LocalDate.now();
        ArrayList<Transaction> transactions = readFromFile();
        for (Transaction item : transactions) {
            if (item.getDate().getMonth() == today.getMonth()) {
                System.out.println(item);

            }
        }
    }

    // search previous month
    public static void searchPreviousMonth() {

        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        boolean found = false;

        ArrayList<Transaction> transactions = readFromFile();
        for (Transaction item : transactions) {
            if (YearMonth.from(item.getDate()).equals(lastMonth)) {

                System.out.println(item);
                found = true;

            }
        }
        if (!found) {
            System.out.println("No previous months to display");
        }

    }

    // search year to date
    public static void searchYearToDate() {
        LocalDate yearStart = Year.now().atDay(1);
        ArrayList<Transaction> transactions = readFromFile();
        for (Transaction item : transactions) {
            if (!item.getDate().isBefore(yearStart)) {
                System.out.println(item);
            }
        }


    }

    // search previous year
    public static void searchPreviousYear() {
        LocalDate yearStart = Year.now().atDay(1);
        LocalDate lastYear = yearStart.minusYears(1);
        ArrayList<Transaction> transactions = readFromFile();
        for (Transaction item : transactions) {
            if (item.getDate().isBefore(yearStart)) {
                System.out.println(item);
            }
        }


    }

    public static void searchVendor() {
        System.out.println("Please enter vendor");
        String vendorSearch = scanner.nextLine();

        ArrayList<Transaction> transactions = readFromFile();
        for (Transaction item : transactions) {
            if (item.getVendor().contains(vendorSearch)) {
                System.out.println(item);

            }
        }


    }


    public static void customSearch() {
        System.out.println("Start Date: ");
        String startDate = scanner.nextLine();

        System.out.println("End Date: ");
        String endDate = scanner.nextLine();

        System.out.println("Description: ");
        String description = scanner.nextLine();

        System.out.println("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.println("Amount: ");
        String amount = scanner.nextLine();// isEmpty()?

        ArrayList<Transaction> transactions = readFromFile();
        ArrayList<Transaction> foundTransactions = new ArrayList<>();


        for (Transaction item : transactions) {
            boolean match = true;


            if (!startDate.isEmpty()) {
                if (item.getDate().isBefore(LocalDate.parse(startDate))) {
                    match = false;
                }

            }
            if (!endDate.isEmpty()) {
                if (item.getDate().isAfter(LocalDate.parse(endDate))) {
                    match = false;
                }
            }
            if (!description.isEmpty()) {
                if (!item.getDescription().toUpperCase().contains(description.toUpperCase())) {
                    match = false;
                }
            }
            if (!vendor.isEmpty()) {
                if (!item.getVendor().toUpperCase().contains(vendor.toUpperCase())) {
                    match = false;
                }
            }
            if (!amount.isEmpty()) {
                if (item.getAmount() != Double.parseDouble(amount)) {
                    match = false;
                }
            }


            if (match) {
                foundTransactions.add(item);
            }
        }
            if (foundTransactions.isEmpty()) {
                System.out.println(Colors.RED + "No transactions found matching your search." + Colors.RESET);
            } else {
                displayAll(foundTransactions);
            }

        }
    }










