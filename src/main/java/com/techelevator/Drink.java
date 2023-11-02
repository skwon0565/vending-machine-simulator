package com.techelevator;

public class Drink extends VendingItems{
    private String message;

    public Drink(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = "Glug Glug, Chug Chug!";
    }
}
