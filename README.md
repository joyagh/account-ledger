# 💰 Cash Money Account Services

A command-line accounting ledger application built in Java. Track deposits and payments, view transaction history, and run reports — all from your terminal.
 
---

## Features

- Add deposits and payments, saved to a local CSV file
- View all transactions, deposits only, or payments only
- Run reports: Month to Date, Previous Month, Year to Date, Previous Year
- Search by vendor or use a custom search with filters for date, description, vendor, and amount
- Color-coded console output (green for deposits, red for payments)
---

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Make sure `transactions.csv` is in the root of the project directory
4. Run `Main.java`
---

## Tech Used

- Java
- File I/O (`BufferedReader`, `BufferedWriter`)
- `LocalDate` / `LocalTime` for date handling
- ANSI escape codes for console colors
---

## File Format

Transactions are stored in `transactions.csv` with the following format:

```
date|time|description|vendor|amount
2026-04-30|09:00|Deposit|Jude|500.00