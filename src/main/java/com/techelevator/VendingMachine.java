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
        int insertedCash = Integer.parseInt(userInput.nextLine());
        setBalance(getBalance() + insertedCash);
    }

    public void selectProduct() {
        displayItems();
        System.out.println("Input the Slot Number to Purchase");
        String slotNumber = userInput.nextLine();
        for (VendingItems vendingItem : vendingItems) {
            String currentVendingItem = vendingItem.getSlotLocation();
            if (currentVendingItem.equalsIgnoreCase(slotNumber) && vendingItem.getStockedAmount() > 0 && vendingItem.getPrice() <= getBalance()) {
                // Entered correct slot AND Item in stock AND enough balance
                System.out.println("\n" + vendingItem.getProductName() + " cost " + vendingItem.getPrice());
                setBalance(getBalance() - vendingItem.getPrice());
                vendingItem.setStockedAmount(vendingItem.getStockedAmount() - 1);
                System.out.println("Remaining Balance: " + getBalance());

                // How do I get this message to print???
                System.out.println(vendingItem.getMessage());

                setBalance(getBalance() - vendingItem.getPrice());
            }
        }

    }
}