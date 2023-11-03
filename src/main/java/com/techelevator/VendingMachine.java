package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
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

    private int balance;
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void displayPurchaseMenu() {
        System.out.println("Current Money Provided: " + getBalance());
        System.out.println();

    }

}