package com.company;

public class BillLine {

    private String itemName;
    private double itemPrice;
    private int count;
    private double itemTotal;
    private BillHeader invH;

    public BillLine(String itemName, double itemPrice, int count, BillHeader invH) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
        this.invH = invH;
        this.setItemTotal(this.count * this.itemPrice);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public BillHeader getInvH() {
        return invH;
    }

    public void setInvH(BillHeader invH) {
        this.invH = invH;
    }
}
