package com.techelevator;

public class Chip extends VendingItems{

    public Chip(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public Chip() {};
    @Override
    public String getMessage() {
        return "Crunch Crunch, It's Yummy!";
    }
}
