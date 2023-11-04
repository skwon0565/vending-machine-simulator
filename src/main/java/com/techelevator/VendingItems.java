package com.techelevator;

public abstract class VendingItems {

    private String slotLocation;
    private String productName;
    private double price;
    private String type;
    private static final int NEW_STOCK = 5;
    private int stockedAmount;

    public VendingItems() {};

    public int getStockedAmount() {
        return stockedAmount;
    }

    public void setStockedAmount(int stockedAmount) {
        this.stockedAmount = stockedAmount;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VendingItems(String slotLocation, String productName, double price, String type) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.stockedAmount = NEW_STOCK;
    }


    public abstract String getMessage();
}
