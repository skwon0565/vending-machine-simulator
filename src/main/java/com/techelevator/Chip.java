package com.techelevator;

public class Chip extends VendingItems{
    private String message;

    public Chip(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = "Crunch Crunch, It's Yummy!";
    }
}
