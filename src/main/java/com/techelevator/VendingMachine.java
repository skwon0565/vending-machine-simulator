package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
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

    private double balance;
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    private final Scanner userInput = new Scanner(System.in);
    public void feedMoney() {
        System.out.println("How much money would you like to insert? (Only Bills Accepted)");
        try {
            int insertedCash = Integer.parseInt(userInput.nextLine());
            if (insertedCash <= 0) {
                throw new NumberFormatException("Please enter a Whole Number");
            }
            setBalance(getBalance() + insertedCash);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a Whole Positive Number");
        }
    }

    public void selectProduct() {
        displayItems();
        System.out.println("Input the Slot Number to Purchase");
        String slotNumber = userInput.nextLine();
        boolean vendingItemIsThere = false;
        for (VendingItems vendingItem : vendingItems) {
            String currentVendingItem = vendingItem.getSlotLocation();
            if (currentVendingItem.equalsIgnoreCase(slotNumber)) {
                if (vendingItem.getStockedAmount() <= 0) {
                    System.out.println("Item not in stock... Try Another Item!");
                } else if (vendingItem.getPrice() > getBalance()) {
                    System.out.println("Not enough funds... Feed more money!");
                } else {
                    // Entered correct slot AND Item in stock AND enough balance
                    System.out.println("\n" + vendingItem.getProductName() + " cost " + vendingItem.getPrice());
                    setBalance(getBalance() - vendingItem.getPrice());
                    vendingItem.setStockedAmount(vendingItem.getStockedAmount() - 1);
                    System.out.println("Remaining Balance: " + getBalance());
                    System.out.println(vendingItem.getMessage());
                    vendingItemIsThere = true;
                    break;
                }
            }
        }
        if (vendingItemIsThere == false) {
            System.out.println("Item does not exist... Try Again!");
        }
    }

    public void finishTransaction() {
        // return the remaining balance in Q, D, N
        System.out.printf("%-8s %-5.2f\n", "Remaining Change: ", getBalance());
        System.out.println("Returned...");
        double change = getBalance();
//        BigDecimal change = new BigDecimal(getBalance());
//        BigDecimal quarter = new BigDecimal(0.24);
//        BigDecimal dime = new BigDecimal(0.09);
//        BigDecimal nickel = new BigDecimal(0.04);
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;
        while (change > 0.04) {
            if (change > 0.24) {
                change -= 0.25;
                quarterCount++;
            } else if (change > 0.09) {
                change -= 0.10;
                dimeCount++;
            } else {
                change -= 0.05;
                nickelCount++;
            }
        }
        System.out.println(quarterCount + " Quarter(s)");
        System.out.println(dimeCount + " Dime(s)");
        System.out.println(nickelCount + " Nickel(s)");

        // reset the balance to 0
        setBalance(0.00);
    }

}
