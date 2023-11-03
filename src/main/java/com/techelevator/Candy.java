package com.techelevator;

public class Candy extends VendingItems{

    public Candy(String slotLocation, String productName, double price, String type) {
        super(slotLocation, productName, price, type);
    }

    public Candy() {};
    @Override
    public String getMessage() {
        return "Munch Munch, Mmm Mmm Good!";
    }
}
