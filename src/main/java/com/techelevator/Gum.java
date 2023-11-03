package com.techelevator;

public class Gum extends VendingItems {

    public Gum(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public Gum() {};
    @Override
    public String getMessage() {
        return "Chew Chew, Pop!";
    }
}
