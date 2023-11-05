package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class VendingMachine {

    List<VendingItems> vendingItems = new ArrayList();

    public void getInventory() {
        File myFile = new File("vendingmachine.csv");
        try (Scanner scanner = new Scanner(myFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] itemArray = line.split("\\|");   // A1|Potato Crisps|3.05|Chip
                double price = Double.parseDouble(itemArray[2]);
                // update the number in stock
                VendingItems myItem = null;
                if (itemArray[0].startsWith("A")) {
                    myItem = new Chip(itemArray[0], itemArray[1], price, itemArray[3]);
                } else if (itemArray[0].startsWith("B")) {
                    myItem = new Candy(itemArray[0], itemArray[1], price, itemArray[3]);
                } else if (itemArray[0].startsWith("C")) {
                    myItem = new Drink(itemArray[0], itemArray[1], price, itemArray[3]);
                } else if (itemArray[0].startsWith("D")) {
                    myItem = new Gum(itemArray[0], itemArray[1], price, itemArray[3]);
                } else {
                    System.out.println("Not a Valid Vending Item");
                }
                vendingItems.add(myItem);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayItems() {
        System.out.printf("%-3s %-20s %-5s %10s\n", "Slot" + "  ||", "Name" + "             ||","Price" + "  ||", "Inventory");
        for (VendingItems vendingItem: vendingItems) {
            System.out.printf("%-8s %-20s %-10.2f %-10s\n", vendingItem.getSlotLocation(), vendingItem.getProductName(), vendingItem.getPrice(), vendingItem.getStockedAmount());
        }
    }

    private BigDecimal balance = new BigDecimal(0.00);
    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    private final Scanner userInput = new Scanner(System.in);
    public void feedMoney() throws IOException {
        System.out.println("How much money would you like to insert? (Only Bills Accepted)");
        try {
            BigDecimal insertedCash = new BigDecimal(userInput.nextLine());
            // int insertedCash = Integer.parseInt(userInput.nextLine());
            if (insertedCash.compareTo(BigDecimal.ZERO) <= 0) {
                throw new NumberFormatException("Please enter a Whole Number");
            }
            setBalance(getBalance().add(insertedCash));
            storeTransactionLog("FEED MONEY:", insertedCash);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a Whole Positive Number");
        }
    }

    public void selectProduct() throws IOException {
        displayItems();
        System.out.println("Input the Slot Number to Purchase");
        String slotNumber = userInput.nextLine();
        boolean vendingItemIsThere = false;
        for (VendingItems vendingItem : vendingItems) {
            String currentVendingItem = vendingItem.getSlotLocation();
            if (currentVendingItem.equalsIgnoreCase(slotNumber)) {
                BigDecimal vendingPrice = new BigDecimal(vendingItem.getPrice());
                if (vendingItem.getStockedAmount() <= 0) {
                    System.out.println("Item not in stock... Try Another Item!");
                } else if (vendingPrice.compareTo(getBalance()) == 1) {
                    System.out.println("Not enough funds... Feed more money!");
                } else {
                    // Entered correct slot AND Item in stock AND enough balance
                    System.out.println("\n" + vendingItem.getProductName() + " cost " + vendingItem.getPrice());
                    setBalance(getBalance().subtract(vendingPrice));
                    vendingItem.setStockedAmount(vendingItem.getStockedAmount() - 1);
                    BigDecimal remainingBalance = getBalance().setScale(2, BigDecimal.ROUND_HALF_EVEN);
                    System.out.println("Remaining Balance: $" + remainingBalance);
                    System.out.println(vendingItem.getMessage());
                    vendingItemIsThere = true;
                    storeTransactionLog(vendingItem.getProductName() + " " + vendingItem.getSlotLocation(), vendingPrice);
                    break;
                }
            }
        }
        if (vendingItemIsThere == false) {
            System.out.println("Item does not exist... Try Again!");
        }
    }

    public void finishTransaction() throws IOException {
        // return the remaining balance in Q, D, N
        System.out.printf("%-8s %-5.2f\n", "Remaining Change: ", getBalance());
        System.out.println("Returned...");
        BigDecimal change = getBalance();
        BigDecimal changeForLog = change;

        BigDecimal quarter = new BigDecimal(0.25);
        BigDecimal dime = new BigDecimal(0.10);
        BigDecimal nickel = new BigDecimal(0.05);

        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        while (change.compareTo(BigDecimal.ZERO) > 0) {
            if (change.compareTo(quarter) >= 0) {
                change = change.subtract(quarter);
                quarterCount++;
            } else if (change.compareTo(dime) >= 0) {
                change = change.subtract(dime);
                dimeCount++;
            } else {
                change = change.subtract(nickel);
                nickelCount++;
            }
        }
        System.out.println(quarterCount + " Quarter(s)");
        System.out.println(dimeCount + " Dime(s)");
        System.out.println(nickelCount + " Nickel(s)");

        // reset the balance to 0
        setBalance(BigDecimal.ZERO.setScale(2));
        storeTransactionLog("GIVE CHANGE:", changeForLog);
    }

    public void storeTransactionLog(String action, BigDecimal amountInfluenced) throws IOException {
        File newFile = new File("Log.txt");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate currentDate = LocalDate.now();
        String dateFormat = currentDate.format(dateFormatter);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a");
        LocalTime currentTime = LocalTime.now();
        String timeFormat = currentTime.format(timeFormatter);

        amountInfluenced = amountInfluenced.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal balance = getBalance().setScale(2, BigDecimal.ROUND_HALF_EVEN);
        String message = dateFormat + " " + timeFormat + " " + action + " $" + amountInfluenced + " $" + balance + "\n";

        PrintWriter writer = null;
        if (newFile.exists()) {
            writer = new PrintWriter(new FileWriter(newFile, true));
        } else {
            writer = new PrintWriter(newFile);
        }
        writer.append(message);
        writer.flush();
        writer.close();
    }

}
