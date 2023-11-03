package com.techelevator;

public class Drink extends VendingItems{

    public Drink(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public Drink() {};
    @Override
    public String getMessage() {
        return "Glug Glug, Chug Chug!";
    }
}
