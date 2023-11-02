package com.techelevator;

public class Candy extends VendingItems{
    private String message;


    public Candy(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = "Munch Munch, Mmm Mmm Good!";
    }
}
