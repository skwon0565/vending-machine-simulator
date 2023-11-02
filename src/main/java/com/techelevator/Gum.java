package com.techelevator;

public class Gum extends VendingItems{
    private String message;

    public Gum(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = "Chew Chew, Pop!";
    }
}
